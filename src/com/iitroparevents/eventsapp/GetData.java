package com.iitroparevents.eventsapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class GetData extends AsyncTask<String, Void, String> {
	/** Called when the activity is first created. */
	private static final int SIM_NETWORK_DELAY = 5000;
	private TabPagerAdapter mParentActivity;
	private String result;
	private Context mApplicationContext;

	public GetData(Context context,TabPagerAdapter parentActivity)
	{
		super();
		mApplicationContext=context;
		mParentActivity = parentActivity;
	}

	@Override
	protected String doInBackground(String... urlparameter) {
		// TODO Auto-generated method stub
		return getData(urlparameter);
	}

	public String getData(String url[]){
		
		InputStream isr = null;
		try {
			URL urlname = new URL(url[0]);
			isr=urlname.openStream();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(SIM_NETWORK_DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			isr.close();

			result=sb.toString();
		}
		catch(Exception e){
			Log.e("check", "Error  converting result "+e.toString());
		}
		
		saveEventsToFile();
		
		return result;
	}

	private void saveEventsToFile() {
		// TODO Auto-generated method stub
		PrintWriter writer = null;
		try {
			FileOutputStream fos = mApplicationContext.openFileOutput(TabPagerAdapter.EVENT_FILENAME, Context.MODE_PRIVATE);
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					fos)));
				writer.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		if (mParentActivity != null) {
			mParentActivity.setRefreshed(result);
		}

	}
}
