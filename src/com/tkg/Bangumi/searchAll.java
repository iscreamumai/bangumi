package com.tkg.Bangumi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;


public class searchAll extends Activity {
	private EditText ch_txtA;
	private Button date_btnA;
	//private Button time_btnA;
	
	private String date_str;
	//private final Activity me;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_all);
        final Activity ttt= this;
        
        ch_txtA = (EditText) findViewById(R.id.ch_number);
        
        date_btnA = (Button) findViewById(R.id.date_btn);
        
       // time_btnA = (Button) findViewById(R.id.time_btn);
        
        final Date dt = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
		String dt_str = fm.format(dt);
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		String tm_str = fmt.format(dt);
		//Toast.makeText(this, dt_str, Toast.LENGTH_LONG);
		date_btnA.setText(dt_str);
		date_str = String.valueOf(dt.getYear() - 100) + String.valueOf(dt.getMonth() + 1) + String.valueOf(dt.getDate());
		//time_btnA.setText(tm_str);
		
		date_btnA.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DatePickerDialog datePickerDialog = new DatePickerDialog(ttt
		                , new DatePickerDialog.OnDateSetListener() {
		            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		                date_btnA.setText(String.format("%04d/%02d/%02d"
		                        , year, monthOfYear+1, dayOfMonth));
		                date_str = String.format("%02d%02d%02d", year, monthOfYear, dayOfMonth);
		            }
		        }, dt.getYear() + 1900, dt.getMonth(), dt.getDate());
		        datePickerDialog.show(); 
				
			}
		});
		
		
		/*time_btnA.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TimePickerDialog timePickerDialog = new TimePickerDialog(ttt
		                , new TimePickerDialog.OnTimeSetListener() {
							
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								time_btnA.setText(String.format("%02d:%02d"
				                        , hourOfDay, minute));
								
							}
						}, dt.getHours(), dt.getMinutes(), true);
				timePickerDialog.show(); 
				
			}
		});*/
        
    }
    
    
    
    
}