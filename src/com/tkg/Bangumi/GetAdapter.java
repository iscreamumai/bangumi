package com.tkg.Bangumi;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GetAdapter extends ArrayAdapter<Item> {
	private LayoutInflater mInflater;
	private TextView mtitle;
	private TextView mtime;
	private boolean gFlg;

	public GetAdapter(Context context, List<Item> objects, boolean flg) {
		super(context, 0, objects);
		gFlg = flg;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int i, View v, ViewGroup vg){
		View view = v;
		
		if(v == null)
			view = mInflater.inflate(R.layout.result_row, null);
		
		Item item = this.getItem(i);
		if(item != null){
			String title = item.getTitle().toString();
			mtitle = (TextView) view.findViewById(R.id.item_title);
			mtitle.setText(title);
			
			String time = item.getTime().toString();
			mtime = (TextView) view.findViewById(R.id.item_time);
			mtime.setText(time);
		}
		
		return view;
	}
	
	public boolean getFlg(){
		return gFlg;
	}
	
	public void setFlg(boolean flg){
		gFlg = flg;
	}

}
