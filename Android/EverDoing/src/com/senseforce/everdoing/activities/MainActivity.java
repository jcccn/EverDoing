package com.senseforce.everdoing.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.senseforce.everdoing.R;
import com.senseforce.everdoing.adapters.DoingListAdapter;

public class MainActivity extends Activity {
    
	private ListView mDoingListView = null;
	private DoingListAdapter mDoingListAdapter = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mDoingListView = (ListView)findViewById(R.id.widget_doing_list);
        mDoingListAdapter = new DoingListAdapter();
        mDoingListView.setAdapter(mDoingListAdapter);
    }
    
    @Override
    public void onDestroy() {
    	mDoingListAdapter = null;
    	mDoingListView = null;
    	super.onDestroy();
    }
}