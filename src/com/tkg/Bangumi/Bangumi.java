package com.tkg.Bangumi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Bangumi extends Activity implements OnClickListener {
	private Button btn0;
	private Button btn1;
	private Button btn2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        btn0 = (Button)this.findViewById(R.id.btn0);
        btn0.setOnClickListener(this);
        
        /*
        btn1 = (Button)this.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);*/
        
        btn2 = (Button)this.findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuItem item0 = menu.add(0,0,0,R.string.menu0);
    	item0.setIcon(android.R.drawable.ic_menu_help);
		return true;
    	
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case 0:
    		AlertDialog.Builder ad = new AlertDialog.Builder(this);
    		ad.setTitle("番組表");
    		ad.setMessage("番組表です");
    		ad.show();
    	}
		return true;
    	
    }
    
    public void onClick(View v){
    	Intent i;
    	switch(v.getId()){
    	case R.id.btn0:
    		i = new Intent(this, searchAll.class);
    		startActivity(i);
    		break;
    		/*
    	case R.id.btn1:
    		i = new Intent(this, searchGenre.class);
    		startActivity(i);
    		break;*/
    	case R.id.btn2:
    		i = new Intent(this, searchFree.class);
    		startActivity(i);
    		break;
    	}
    }
}