package com.iitroparevents.eventsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.learn2crack.tab.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Cultural extends Fragment{
	View cultural;
	String text="Cultural";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		
		cultural = inflater.inflate(R.layout.cultural_frag, container, false);
		update();
		
		setRetainInstance(true);
		return cultural;
	}
	
	public void update()
	{
		LinearLayout main=(LinearLayout) (cultural.findViewById(R.id.main_layout));
		if(text.equals("Loading..."))
		{
			TextView textview=new TextView(main.getContext());
			textview.setText(text);
			textview.setTextColor(Color.WHITE);
			main.addView(textview);
		}
		else
		{
			if(main.getChildCount() > 0) 
			    main.removeAllViews(); 
			
			JSONArray jarray;
			try {
				jarray = new JSONArray(text);
			for (int i = 0; i < jarray.length(); i++) {
				JSONObject json=jarray.getJSONObject(i);
			    CustomRelativeView event = new CustomRelativeView(main.getContext(),json, i);
			    main.addView(event);
			}
			View ruler = new View(main.getContext()); 
		    ruler.setBackgroundColor(Color.LTGRAY);
		    main.addView(ruler, new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 2));
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
