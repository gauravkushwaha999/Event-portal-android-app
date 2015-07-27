package com.iitroparevents.eventsapp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

	ArrayList<Fragment> fragments=new ArrayList<Fragment>();
	FragmentManager mFragmentManger;
	Context mApplicationContext;
	ArrayList<String> Category=new ArrayList<String>();
	public static final String EVENT_FILENAME = "Events.txt";
	private boolean downloading=false;
	public String[] rawFeeds={"","","","","",""};
	private String result="";
	private String downloadingEvents="Loading...";
	private static final long TWO_MIN =  2*60*1000;
	
	String url="http://10.1.33.175/Events_Final/jsongetdata.php";   //10.1.35.196 192.168.137.200
	private boolean mIsFresh = false;

	public TabPagerAdapter(MainActivity parentActivity,FragmentManager fm) {
		super(fm);
		mFragmentManger = fm;
		mApplicationContext=parentActivity;
		// TODO Auto-generated constructor stub
		Log.i("check", "TabAdapterInitialize");
	}

	@Override
	public Fragment getItem(int i) {
		
		if(ensureData())
		{
			loadEventsFromFile();
		}
			String feed=rawFeeds[i];
			
			switch(i)
			{
			case 0:
				Sports sport=new Sports();
				
				if(!feed.equals(""))
				    sport.text=feed;
				else
					sport.text=downloadingEvents;
				return sport;
			case 1:
				Seminar sem=new Seminar();
				if(!feed.equals(""))
				sem.text=feed;
				else
					sem.text=downloadingEvents;
				return sem;
			case 2:
				Cultural cul=new Cultural();
				if(!feed.equals(""))
				cul.text=feed;
				else
					cul.text=downloadingEvents;
				return cul;
			case 3:
				Competition comp=new Competition();
				if(!feed.equals(""))
				comp.text=feed;
				else
					comp.text=downloadingEvents;
				return comp;
			case 4:
				ClubEvents club=new ClubEvents();
				if(!feed.equals(""))
				club.text=feed;
				else
					club.text=downloadingEvents;
				return club;
			case 5:
				Others others=new Others();
				if(!feed.equals(""))
				others.text=feed;
				else
					others.text=downloadingEvents;
				return others;
			default:
				return null;
			}

	}
	
	@Override
	public int getItemPosition(Object object)
	{
		return POSITION_NONE;
	}
	
	private boolean ensureData() {
		// TODO Auto-generated method stub
		
		if(mIsFresh)
		{
			mIsFresh = (System.currentTimeMillis() - mApplicationContext.getFileStreamPath(
					EVENT_FILENAME).lastModified()) < TWO_MIN;
			
			return true;
		}
		else
		{
			
			if(!downloading)
			{
				Toast.makeText(mApplicationContext, "Updating Events", Toast.LENGTH_SHORT).show();
				Log.i("check", "Downloading new events");
				GetData downloadTask = new GetData(mApplicationContext,TabPagerAdapter.this);
				downloadTask.execute(url);
				downloading=true;
			}
			
			return false;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6; //No of Tabs
	}

	public void setRefreshed(String feed) {
		// TODO Auto-generated method stub
		result=feed;
//		Log.i("check", feed);
		for(int i=0;i<6;i++)
			rawFeeds[i]="";
		
		parseJSON();
		mIsFresh = true;
		downloading=false;
	}

	public void parseJSON()
	{
		Category.add("sports");
		Category.add("seminar");
		Category.add("cultural");
		Category.add("competitions");
		Category.add("club_events");
		Category.add("others");
		
		String data="";
		try {
			JSONArray jArray = new JSONArray(result);
			
			for(int i=0; i<jArray.length();i++,data=""){
				JSONObject json = jArray.getJSONObject(i);
				data=json.toString();				
				rawFeeds[Category.indexOf(json.getString("Category"))] += data+",";
			}
			
			for(int i=0;i<rawFeeds.length;i++)
			{
				StringBuilder sb=new StringBuilder(rawFeeds[i]);
				sb.deleteCharAt(sb.length()-1);
				rawFeeds[i]="["+sb.toString()+"]";
			}
			Log.i("help", "fgahgdsh");

		} catch (Exception e) {
			// TODO: handle exception
			Log.i("check", "Exception");
		}
	}
	
	private void loadEventsFromFile() {
		BufferedReader reader = null;

		try {
			FileInputStream fis =mApplicationContext.openFileInput(EVENT_FILENAME);
			reader = new BufferedReader(new InputStreamReader(fis));
			String s = null;
			while (null != (s = reader.readLine())) {
				result += s;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}