package com.tkg.Bangumi;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;


public class searchGenre extends Activity {

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* LinearLayout ll = new LinearLayout(this);
        ll.setBackgroundColor(Color.WHITE);
        
        TextView tv = new TextView(this);
        ll.addView(tv);
        tv.setText("searchGenre");
        tv.setTextColor(Color.MAGENTA);
        tv.setTextSize(20);
        
        setContentView(ll);*/
        
         setContentView(R.layout.genre);
        
    }
    
    
}