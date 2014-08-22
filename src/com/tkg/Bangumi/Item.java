package com.tkg.Bangumi;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{
	private String service;
	private String channel;
	private String c_name;
	private String title;
	private String explanation;
	private long air_date;
	private String wday;
	private Date start;
	private Date end;
	private int duration;
	private String on_air;
	private String hikari;
	private String[] person;
	private String program_title;
	private String episode_title;
	private String program;
	private String wiki;
	private String genre;
	private String picture_mode;
	private String period;
	private String pay_per_view;
	private String restricted;
	private String particular_mode;
	private String no_scramble;
	private String live;
	private String daikaiho;
	private String caption;
	private String audio;
	private String wide;
	private String created;
	private String updated;
	
	
	
	private String time;
	public Item(){
		title = "";
		time = "";
		explanation = "";
	}
	
	public String getTitle(){
		
		return title;
	}
	
	public void setTitle(String str){
		title = str;
	}
	
	public String getTime(){
		return time;
	}
	public void setTime(String str){
		time = str;
	}
	public String getEx(){
		return explanation;
	}
	public void setEx(String str){
		explanation  = str;
	}
	
	public Date getStart(){
		return start;
	}
	public void setStart(Date d){
		start = d;
	}
	
	public Date getEnd(){
		return end;
	}
	public void setEnd(Date d){
		end = d;
	}
}
