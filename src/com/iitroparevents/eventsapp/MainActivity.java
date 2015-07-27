package com.iitroparevents.eventsapp;

import com.learn2crack.tab.R;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
//import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	ViewPager Tab;
    TabPagerAdapter TabAdapter;
	ActionBar actionBar;
	Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        TabAdapter = new TabPagerAdapter(MainActivity.this,getSupportFragmentManager());
        
        Tab = (ViewPager)findViewById(R.id.pager);
        
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                       
                    	actionBar = getActionBar();
                    	actionBar.setSelectedNavigationItem(position);
                    	}
                });
        Tab.setAdapter(TabAdapter);
        
        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(),tab.getText()+ "TabReselected", Toast.LENGTH_SHORT).show();
			}

			@Override
			 public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	          
				int position=tab.getPosition();
				
	            Tab.setCurrentItem(position);	            
	        }

			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub	
			}};
			//Add New Tab
			actionBar.addTab(actionBar.newTab().setText("Sports").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Seminar").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Cultural").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Competition").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Club Events").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Others").setTabListener(tabListener));

    }
    
    @Override
	public void onBackPressed() {
	    moveTaskToBack(true);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        moveTaskToBack(true);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
