package com.tkg.Bangumi;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResult extends ListActivity {
	private String getUrl;
	private int offset;
	
	private ArrayList alist;
	private GetAdapter gAdapter;

	private ListView lv;
	private TextView totalText;
	
	private boolean fflg;
	
	public View footer;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.result);
		
		footer = View.inflate(this, R.layout.footer, null);
		//getListView().addFooterView(footer);
		
		Intent i = getIntent();
		getUrl = i.getStringExtra("GETURL");
		offset = i.getIntExtra("OFFSET", 0);
		fflg = false;
		
		alist = new ArrayList();
		gAdapter = new GetAdapter(this, alist, true);
		//setListAdapter(gAdapter);
		ParserTask task = new ParserTask(this, gAdapter);
		task.execute(getUrl + "&offset=" + offset);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int pos, long id){
		if(v.getId() == R.id.footer){
			offset += 10;
			//alist = new ArrayList();
			
			gAdapter = new GetAdapter(this, alist, false);
			ParserTask task = new ParserTask(this, gAdapter);
			task.execute(getUrl + "&offset=" + offset);
			
		}else{
			Item item = (Item) alist.get(pos);
			Intent in = new Intent(this, Detail.class);
			/*in.putExtra("TITLE", item.getTitle());
			in.putExtra("TIME", item.getTime());
			in.putExtra("BODY", item.getEx());*/
			in.putExtra("SET_ITEM", item);
			startActivity(in);
		}
	}
	
	public void addHeader(String str){
		totalText = (TextView) findViewById(R.id.totalTxt);
		totalText.setText(str);
	}
	


}
