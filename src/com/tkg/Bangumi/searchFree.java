package com.tkg.Bangumi;

import java.net.URLEncoder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.LayoutParams;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class searchFree extends Activity implements OnClickListener, OnCheckedChangeListener {
	private EditText ed;
	private Button btn;
	
	private RadioGroup rGroup;
	private RadioButton rButton0;
	private RadioButton rButton1;
	private RadioButton rButton2;
	
	private CheckBox cButton;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        LinearLayout ll = new LinearLayout(this);
        //ll.setBackgroundColor(Color.WHITE);
        ll.setOrientation(LinearLayout.VERTICAL);
        
        setContentView(ll);
        
        ed = new EditText(this);
        ed.setWidth(100);
        ed.setHint("�t���[���[�h");
        ll.addView(ed, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        
        //���W�I�{�^��0
        rButton0 = new RadioButton(this);
        rButton0.setId(0);
        rButton0.setText("");
        rButton0.setTextColor(Color.WHITE);
        
        //���W�I�{�^��1
        rButton1 = new RadioButton(this);
        rButton1.setId(1);
        rButton1.setText("");
        rButton1.setTextColor(Color.WHITE);
        
        //���W�I�{�^��2
        rButton2 = new RadioButton(this);
        rButton2.setId(2);
        rButton2.setText("");
        rButton2.setTextColor(Color.WHITE);
        
        //���W�I�O���[�v
        rGroup = new RadioGroup(this);
        rGroup.addView(rButton0);
        rGroup.addView(rButton1);
        rGroup.addView(rButton2);
        rGroup.check(0);
        rGroup.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        ll.addView(rGroup);
        
        //�`�F�b�N�{�b�N�X
        cButton = new CheckBox(this);
        cButton.setText("���l�����ԑg������");
        cButton.setTextColor(Color.WHITE);
        cButton.setChecked(false);
        cButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        ll.addView(cButton);
        cButton.setOnCheckedChangeListener(this);
        
        
        
        
        btn = new Button(this);
        btn.setText("����");
        btn.setLayoutParams( new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        ll.addView(btn);
        btn.setOnClickListener(this);
        
        // setContentView(R.layout.main);
        
    }
    @Override
    public void onCheckedChanged(CompoundButton cb, boolean isChecked){
    	if(isChecked){
	    	new AlertDialog.Builder(this)
	    	.setTitle("����")
	    	.setMessage("�������͐��l�����ԑg�̔ԑg���ł��B\n���Ȃ���20�Έȏ�ł����H")
	    	.setPositiveButton("�͂�", new DialogInterface.OnClickListener() {
	    		@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			})
			.setNegativeButton("������", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					cButton.setChecked(false);
				}
			})
			.show();
    	}
    }
    @Override
	public void onClick(View v) {
		String str = ed.getText().toString();
		if(str.length() == 0){
			new AlertDialog.Builder(this)
			.setTitle("�G���[")
			.setMessage("�L�[���[�h����͂��Ă�������")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub					
				}
			})
			.show();
		}else{
			//Toast.makeText(this, rGroup.getCheckedRadioButtonId() + "," + str, Toast.LENGTH_LONG).show();
			String getUrl = "";
			String encStr = URLEncoder.encode(str);
			switch( rGroup.getCheckedRadioButtonId() ){
			case 0:
				if(cButton.isChecked()){
					getUrl = getUrl + "/";
				}else{
					getUrl = getUrl + "/";
				}
				break;
			case 1:
				getUrl = getUrl + "/";
				break;
			case 2:
				if(cButton.isChecked()){
					getUrl = getUrl + "/";
				}else{
					getUrl = getUrl + "/";
				}
				break;
			}
			getUrl = getUrl + "query:" + encStr + "/?limit=10";
			Intent i = new Intent(this, SearchResult.class);
			i.putExtra("GETURL", getUrl);
			i.putExtra("OFFSET", 0);
			startActivity(i);
			
		}
	}
    
    
}