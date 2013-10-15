package com.example.localservice;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class ActivityLocalService extends ListActivity implements android.view.View.OnClickListener{

	private LocalService localService;
	private ArrayList<String> arr;
	private ArrayAdapter<String> _adapter;
	
	private ServiceConnection serviceConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder iBinder) {
			// TODO Auto-generated method stub
			localService = ((LocalService.MyBinder)iBinder).getService();
			Toast.makeText(ActivityLocalService.this, "Connected", Toast.LENGTH_LONG).show();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			localService = null;
			Toast.makeText(ActivityLocalService.this, "Disconnected", Toast.LENGTH_LONG).show();
		}
		
		
	};
	
	@Override
	protected void onResume(){
		super.onResume();
		bindService(new Intent(ActivityLocalService.this, LocalService.class), serviceConnection, 
				Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		unbindService(serviceConnection);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_local_service);
		
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(ActivityLocalService.this);
		
		arr = new ArrayList<String>();
		_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,android.R.id.text1, arr);
		
		setListAdapter(_adapter);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_local, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view.getId() == R.id.button1){
			Toast.makeText(view.getContext(), "Size of list: " +localService.getList().size(), Toast.LENGTH_LONG).show();
			
			arr.clear();
			arr.addAll(localService.getList());
			_adapter.notifyDataSetChanged();
			
		}
	}

	

}
