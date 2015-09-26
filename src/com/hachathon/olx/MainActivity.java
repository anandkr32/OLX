package com.hachathon.olx;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
     
	private Spinner mLocationSpinner;
	private EditText mCategory;
	private EditText mTitle;
	private EditText mDescription;
	private EditText mPrice;
	private ArrayList<String> mCities;
	private ImageView img;
	private Button CameraStart;
	private Button submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		
	}
	
	private void init(){
		mPrice = (EditText)findViewById(R.id.price);
		mCategory = (EditText)findViewById(R.id.category);
		mCategory.setFocusableInTouchMode(false);
		
		mCategory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,CategoryActivity.class);
				startActivityForResult(i, 1);
			}
		});
		
		img = (ImageView) findViewById(R.id.image);
		CameraStart = (Button) findViewById(R.id.imageClick);
		
		
		CameraStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                startActivityForResult(cameraIntent, 2); 
			}
		});
		
		
		mTitle = (EditText) findViewById(R.id.title);
		mDescription = (EditText) findViewById(R.id.description);
		submit = (Button)findViewById(R.id.submit);
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				invalidateValues();
			}
		});
		
		mLocationSpinner = (Spinner)findViewById(R.id.location_spinner);
		
		mCities = new ArrayList<String>();
		mCities.add("Location");
		mCities.add("New Delhi");
		mCities.add("Kolkata");
		mCities.add("Mumbai");
		mCities.add("Bangalore");
		mCities.add("Chennai");
		mCities.add("Noida");
		mCities.add("Jaipur");
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,mCities);
        
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLocationSpinner.setAdapter(adapter);
	}
	
	
	private void invalidateValues(){
		String title = mTitle.getText().toString();
		String des = mDescription.getText().toString();
		String category = mCategory.getText().toString();
		String price = mPrice.getText().toString();
		if("".equals(title)){
			Toast.makeText(this,"title cant be empty",Toast.LENGTH_LONG).show();
			return;
		} else if("".equals(des)){
			Toast.makeText(this,"description cant be empty",Toast.LENGTH_LONG).show();
			return;
		} else if("".equals(category)){
			Toast.makeText(this,"Please choose a category",Toast.LENGTH_LONG).show();
			return;
		}
		
		Toast.makeText(this,"your ad is submitted", Toast.LENGTH_LONG).show();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch(requestCode) {
	    case 1:
	        if (resultCode == RESULT_OK) {
	            Bundle res = data.getExtras();
	            String group = res.getString("Group");
	            String child = res.getString("Child");
	            mCategory.setText("Category: "+group+"      Product: "+child);
	            
	        }
	        break;
	        
	    case 2:
	    	if(resultCode == RESULT_OK){
	    		Bitmap cameraImage = (Bitmap) data.getExtras().get("data"); 
                img.setImageBitmap(cameraImage);
	    	}
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
