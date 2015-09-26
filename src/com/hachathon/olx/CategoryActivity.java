package com.hachathon.olx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
 
public class CategoryActivity extends Activity {
 
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
 
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        intent = this.getIntent();
        // preparing list data
        prepareListData();
 
        listAdapter = new MyExpandableListAdapter(this, listDataHeader, listDataChild);
        init();
        // setting list adapter
        Log.d("anand","expListView   "+expListView);
        expListView.setAdapter(listAdapter);
    }
 
    
    private void init(){
    	expListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Log.d("anand","onChildClick listDataHeader  "+listDataHeader+" ");
                intent.putExtra("Group",listDataHeader.get(groupPosition));

                String s = (listDataChild.get(listDataHeader.get(groupPosition))).get(childPosition);
                intent.putExtra("Child",s);
			    Activity a = CategoryActivity.this;
			    a.setResult(RESULT_OK, intent);
			    a.finish();
                return false;
			}
		});
    }
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Mobile");
        listDataHeader.add("Cars");
        listDataHeader.add("Electronics");
 
        // Adding child data
        List<String> Mobile = new ArrayList<String>();
        Mobile.add("Windows");
        Mobile.add("Nokia");
        Mobile.add("I phone");
        Mobile.add("Black Berry");
        Mobile.add("Android");
 
        List<String> Car = new ArrayList<String>();
        Car.add("Fiat");
        Car.add("Honda");
        Car.add("Maruti");
        Car.add("Hyundai");
        Car.add("VolksWagon");
        Car.add("Skoda");
 
        List<String> Electronics = new ArrayList<String>();
        Electronics.add("Air Conditioned");
        Electronics.add("TV");
        Electronics.add("Refridgerator");
        Electronics.add("Camera");
        Electronics.add("Washing machine");
 
        listDataChild.put(listDataHeader.get(0), Mobile); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Car);
        listDataChild.put(listDataHeader.get(2), Electronics);
    }
}