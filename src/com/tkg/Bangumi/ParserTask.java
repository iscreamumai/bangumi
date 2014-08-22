package com.tkg.Bangumi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public class ParserTask extends AsyncTask<String, Integer, GetAdapter> {
	private SearchResult act;
	private GetAdapter gAdapter;
	private ProgressDialog progress;
	
	private ArrayList<Item> additem;
	
	private int totalNum;

	public ParserTask(SearchResult sr, GetAdapter ga){
		act = sr;
		gAdapter = ga;
	}
	
	
	@Override
	protected void onPreExecute(){
		progress = new ProgressDialog(act);
		progress.setMessage("Now Loading...");
		progress.show();
	}
	@Override
	protected GetAdapter doInBackground(String... arg0) {
		GetAdapter result = null;
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest req = new HttpGet(arg0[0]);
		HttpResponse res = null;
		try{
			res = client.execute(req);
		}catch(ClientProtocolException e){
			
		}catch(IOException e){
			
		}
		String json = null;
		if(res != null  && res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			HttpEntity enti = res.getEntity();
		
			try{
				json = EntityUtils.toString(enti);
			}catch(ParseException e){
				
			}catch(IOException e){
				
			}finally{
				try{
					enti.consumeContent();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			client.getConnectionManager().shutdown();
			
			
			try {
				result = parseJson(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		return result;
	}
	
	@Override
	protected void onPostExecute(GetAdapter result){
		progress.dismiss();
		act.addHeader("Total:" + String.valueOf(totalNum) + "件");
		
		if(gAdapter.getFlg()){
			if(totalNum > 10)
				act.getListView().addFooterView(act.footer);
			act.setListAdapter(result);
		}else{
			act.getListView().removeFooterView(act.footer);
			ArrayList<Item> list = additem;
			for(int i = 0; i< list.size(); i++){
				Item item = list.get(i);
				gAdapter.add(item);
			}
			gAdapter.notifyDataSetChanged();
			act.getListView().addFooterView(act.footer);
		}
		

	}


	public GetAdapter parseJson(String js) throws IOException{
		String json = js;
		JSONObject JB;
		//ArrayList ch_all = new ArrayList();
		Log.v("TAG0", json);
		try {
			JB = new JSONObject(json);
			int num = JB.getInt("n_hits");
			totalNum = num;
			JSONArray records = JB.getJSONArray("records");
			int count = records.length();
			Item currentItem = null;
			additem = new ArrayList<Item>();
			for(int i=0; i<count; i++){
				JSONObject rec1 = records.getJSONObject(i);
				
				String ttl = rec1.getString("title");
				ttl = URLDecoder.decode(ttl);
				
				//時刻フォーマット
				long lon = rec1.getLong("start");
				lon = lon * 1000;
				Date dt = new Date(lon);
				
				SimpleDateFormat fm = new SimpleDateFormat("MM/dd HH:mm", Locale.JAPAN);
				String tim = fm.format(dt).toString();
				
				long lonE = rec1.getLong("end");
				lonE*=1000;
				Date dtE = new Date(lonE);

				String Time;
				
				String ch = rec1.getString("channel");
				String ch_name = rec1.getString("channel_name");
				
				ch_name = URLDecoder.decode(ch_name);
				
				Time = "Ch." + ch + " " + ch_name + "\n" + tim;
				
				String body = rec1.getString("explanation");
				body = URLDecoder.decode(body);
				
				
				currentItem = new Item();
				currentItem.setTitle(ttl);
				currentItem.setTime(Time);
				currentItem.setEx(body);
				currentItem.setStart(dt);
				currentItem.setEnd(dtE);
				if(gAdapter.getFlg()){
					gAdapter.add(currentItem);
				}else{
					additem.add(currentItem);
				}
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return gAdapter;
		
	}

}
