package com.iitroparevents.eventsapp;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.JSONException;
import org.json.JSONObject;

import com.learn2crack.tab.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomRelativeView extends RelativeLayout {

	Context mApplicationContext;
	TextView eventName;
	TextView date;
	TextView Time;
	TextView Duration;
	TextView DurationText;
	TextView Description;
	TextView Venue;
	TextView VenueText;
	TextView Coordinator;
	TextView CoordinatorText;
	ImageView Clock;
	ImageView Calender;
	ImageView selection;
	ImageView button;
	boolean clicked=false;

	public CustomRelativeView(Context context)
	{
		super(context);
	}

	public CustomRelativeView(Context context,JSONObject json,int i) {
		super(context);
		// TODO Auto-generated constructor stub
		mApplicationContext = context;
		eventName=new TextView(mApplicationContext);
		date=new TextView(mApplicationContext);
		Time=new TextView(mApplicationContext);
		Duration=new TextView(mApplicationContext);
		DurationText=new TextView(mApplicationContext);
		Description=new TextView(mApplicationContext);
		Venue=new TextView(mApplicationContext);
		selection=new ImageView(mApplicationContext);
		VenueText=new TextView(mApplicationContext);
		Coordinator=new TextView(mApplicationContext);
		CoordinatorText=new TextView(mApplicationContext);
		Calender=new ImageView(mApplicationContext);
		Calender.setImageResource(R.drawable.calendar);
		Clock=new ImageView(mApplicationContext);
		Clock.setImageResource(R.drawable.clock);
		button = new ImageView(mApplicationContext);
		setContent(json, i);
	}

	void setContent(JSONObject json,int i)
	{			
		RelativeLayout.LayoutParams rlpt;

		eventName.setId(i+12);
		date.setId(i+1);
		Time.setId(i+2);
		Duration.setId(i+3);
		Description.setId(i+4);
		Venue.setId(i+5);
		Coordinator.setId(i+6);
		Calender.setId(i+7);
		Clock.setId(i+8);
		VenueText.setId(i+9);
		DurationText.setId(i+10);
		CoordinatorText.setId(i+11);
		selection.setId(i+13);
		button.setId(i+14);

		try {
			eventName.setText(json.getString("Event"));
			date.setText(json.getString("Date(date)")+"-"+json.getString("Date(Month)")+"-"+json.getString("Date(YEAR)"));
			Time.setText(json.getString("Time(HH)")+":"+json.getString("Time(MM)")+" "+json.getString("Time(AM/PM)"));
			DurationText.setText(json.getString("Duration"));
			Description.setText(json.getString("Event Description"));
			VenueText.setText(json.getString("Venue"));
			CoordinatorText.setText(json.getString("Coordinator_ID"));


			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			eventName.setTextAppearance(mApplicationContext, R.style.normalText);
			eventName.setLayoutParams(rlpt);
			eventName.setTextSize(15);
			eventName.setPadding(10, 10, 0, 0);
			eventName.setTextAppearance(mApplicationContext, R.style.boldText);
			this.addView(eventName);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.height=40;
			rlpt.width=40;
			Calender.setPadding(10, 5, 0, 0);
			rlpt.addRule(RelativeLayout.BELOW,eventName.getId());
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			this.addView(Calender,rlpt);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			date.setTextSize(15);
			date.setPadding(5, 5, 0, 0);
			date.setTextAppearance(mApplicationContext, R.style.normalText);
			rlpt.addRule(RelativeLayout.RIGHT_OF,i+7);
			rlpt.addRule(RelativeLayout.BELOW,eventName.getId());
			this.addView(date,rlpt);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.height=50;
			rlpt.width=50;
			Clock.setPadding(20, 5, 0, 0);
			rlpt.addRule(RelativeLayout.BELOW,eventName.getId());
			rlpt.addRule(RelativeLayout.RIGHT_OF,i+1);
			this.addView(Clock,rlpt);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			Time.setTextSize(15);
			Time.setPadding(5, 5, 0, 0);
			Time.setTextAppearance(mApplicationContext, R.style.normalText);
			rlpt.addRule(RelativeLayout.RIGHT_OF,i+8);
			rlpt.addRule(RelativeLayout.BELOW,eventName.getId());
			this.addView(Time,rlpt);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			rlpt.addRule(RelativeLayout.BELOW,i+7);
			selection.setImageResource(R.drawable.tripledots);
			selection.setPadding(10, 10, 0, 0);
			selection.setLayoutParams(rlpt);
			this.addView(selection);
			
			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			rlpt.addRule(RelativeLayout.BELOW,i+7);
			button.setPadding(10, 10, 0, 10);
			button.setLayoutParams(rlpt);
			button.setImageResource(R.drawable.notifyicon);
			this.addView(button);


			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			rlpt.addRule(RelativeLayout.BELOW,i+14);
			Venue.setText("Venue : ");
			Venue.setTextAppearance(mApplicationContext, R.style.normalText);
			Venue.setPadding(10, 5, 0, 0);
			Venue.setTextAppearance(mApplicationContext, R.style.boldText);
			Venue.setLayoutParams(rlpt);
			Venue.setTextSize(13);


			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.RIGHT_OF,i+5);
			rlpt.addRule(RelativeLayout.ALIGN_TOP,i+5);
			VenueText.setTextAppearance(mApplicationContext, R.style.normalText);
			VenueText.setPadding(5, 5, 0, 0);
			VenueText.setLayoutParams(rlpt);
			VenueText.setTextSize(13);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			rlpt.addRule(RelativeLayout.BELOW,i+9);
			Duration.setText("Duration : ");
			Duration.setPadding(10, 5, 0, 0);
			Duration.setTextAppearance(mApplicationContext, R.style.boldText);
			Duration.setLayoutParams(rlpt);
			Duration.setTextSize(13);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.RIGHT_OF,i+3);
			rlpt.addRule(RelativeLayout.ALIGN_TOP,i+3);
			DurationText.setPadding(5, 5, 0, 0);
			DurationText.setTextAppearance(mApplicationContext, R.style.normalText);
			DurationText.setLayoutParams(rlpt);
			DurationText.setTextSize(13);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			rlpt.addRule(RelativeLayout.BELOW,i+10);
			Description.setTextAppearance(mApplicationContext, R.style.normalText);
			Description.setPadding(10, 5, 0, 0);
			Description.setLayoutParams(rlpt);
			Description.setTextSize(13);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			rlpt.addRule(RelativeLayout.BELOW,i+4);
			Coordinator.setText("Coordinator_ID : ");
			Coordinator.setTextAppearance(mApplicationContext, R.style.normalText);
			Coordinator.setPadding(10, 5, 0, 0);
			Coordinator.setTextAppearance(mApplicationContext, R.style.boldText);
			Coordinator.setLayoutParams(rlpt);
			Coordinator.setTextSize(13);

			rlpt=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlpt.addRule(RelativeLayout.RIGHT_OF,i+6);
			rlpt.addRule(RelativeLayout.ALIGN_TOP,i+6);
			CoordinatorText.setLayoutParams(rlpt);
			CoordinatorText.setTextAppearance(mApplicationContext, R.style.normalText);
			CoordinatorText.setPadding(5, 5, 0, 15);
			CoordinatorText.setTextSize(13);


			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					// Perform action on click
					try {
						addCalenderEvent();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});

			selection.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(!clicked)
					{
						addView(Venue);
						addView(VenueText);
						addView(Duration);
						addView(DurationText);
						addView(Description);
						addView(Coordinator);
						addView(CoordinatorText);
//						addView(button);

						clicked=true;
					}
					else
					{
						removeView(Venue);
						removeView(VenueText);
						removeView(Duration);
						removeView(DurationText);
						removeView(Description);
						removeView(Coordinator);
						removeView(CoordinatorText);
//						removeView(button);

						clicked=false;
					}
				}
			});

			View ruler = new View(mApplicationContext); 
			ruler.setBackgroundColor(Color.LTGRAY);
			this.addView(ruler, new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 2));
			RelativeLayout.LayoutParams rlp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
			this.setLayoutParams(rlp);
			this.setBackgroundResource(R.drawable.gradient);
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressLint("SimpleDateFormat")
	public void addCalenderEvent() throws ParseException
	{	
		String startdate=(String) date.getText();
		
		Date datest=new SimpleDateFormat("dd-MM-yyyy").parse(startdate);
		Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra("beginTime", datest.getTime());
		intent.putExtra("allDay", true);
		intent.putExtra("rrule", "FREQ=YEARLY");
		intent.putExtra("endTime", datest.getTime()+60*60*1000);
		intent.putExtra("title", eventName.getText());
		intent.putExtra("description", Description.getText());
		intent.putExtra("eventLocation", VenueText.getText());
		mApplicationContext.startActivity(intent);

	}
}
