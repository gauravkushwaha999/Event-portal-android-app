package com.iitroparevents.eventsapp;

import com.learn2crack.tab.R;
import com.parse.Parse;
import com.parse.PushService;

import android.app.Application;

public class MainApplication extends Application {
@Override
public void onCreate() {
	// TODO Auto-generated method stub
	super.onCreate();
	Parse.initialize(this, "cxPKr9sJoY3Yu54MdkAagohwNslq9wacSj4PKv4I", "WrbpIXoDddhF8k45r7pzWuVLoesytJngl7NEOxRC");
    PushService.setDefaultPushCallback(this, MainActivity.class,R.drawable.logo_iit_ropar);
}
}
