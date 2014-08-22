package com.tkg.Bangumi;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

public class Detail extends Activity implements OnClickListener {
	private TextView title;
	private TextView time;
	private TextView body;
	private Button gCal;
	private Item item;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		Intent in = getIntent();
		item = (Item) in.getSerializableExtra("SET_ITEM");
		String ttl = item.getTitle();
		String tm = item.getTime();
		String bd = item.getEx();
		
		title = (TextView) findViewById(R.id.detail_title);
		title.setText(ttl);
		
		time = (TextView) findViewById(R.id.detail_time);
		time.setText(tm);
		
		body = (TextView) findViewById(R.id.detail_body);
		body.setText(bd);
		
		gCal = (Button) findViewById(R.id.gCal_button);
		gCal.setOnClickListener(this);
		
		
	}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.gCal_button:
			setGCal();
			break;
		}
	}
	
	private void setGCal(){
		String evPath = "";
		if(Build.VERSION.SDK_INT <= 7){
			evPath = "content://calendar/events";
		}else{
			evPath = "content://com.android.calendar/events";
		}
		
		Uri calUri = Uri.parse(evPath);
		ContentValues values = new ContentValues(); 
        ContentResolver cr = getContentResolver();
        
        long startLongDay = getLongDay(item.getStart()); 
        long endLongDay = getLongDay(item.getEnd()); 
		values.put("calendar_id",1); 
		values.put("title",item.getTitle()); 
		//values.put("eventLocation","location"); 
		values.put("description",item.getEx()); 
		values.put("dtstart",startLongDay); 
		values.put("dtend",endLongDay); 
		//values.put("eventTimezone",Time.TIMEZONE_UTC); 
		values.put("eventTimezone",TimeZone.getDefault().getDisplayName(Locale.JAPAN)); 
		values.put("allDay",0); 
		values.put("hasAlarm",0);
		values.put("_sync_account_type", "com.google"); 
		/*values.put("method", 1);
		values.put("minutes", 60);*/
		
		cr.insert(calUri,values); 
		Toast.makeText(this,"“o˜^‚µ‚Ü‚µ‚½",Toast.LENGTH_SHORT).show(); 
	}
	
	public long getLongDay(Date dt){ 
        long time=0; 
        try{ 
            Time times = new Time(); 
            times.timezone = TimeZone.getDefault().getDisplayName(Locale.JAPANESE); 
            times.set(dt.getTime()); 
            time = times.normalize(true); 
        }catch (Exception e){ 
            e.printStackTrace(); 
        } 
        return time; 
    } 

}
