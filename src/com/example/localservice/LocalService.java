package com.example.localservice;

import java.util.ArrayList;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service{

	ArrayList<String> list = new ArrayList<String>();
	private IBinder mBinder = new MyBinder();
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	@Override
	public int onStartCommand(Intent intent, int flaf, int startid){
	
		Random random = new Random();
	
		if(random.nextBoolean()){
			list.add("Android");			
		}
		if(random.nextBoolean()){
			list.add("Linux");
		}
		if(random.nextBoolean()){
			list.add("i-Phone");
		}
		if(random.nextBoolean()){
			list.add("Lumia");
		}
		if(list.size() > 20){
			list.remove(0);
		}
		
		return Service.START_NOT_STICKY;
	}

	@Override
	public void onCreate(){
		
	}
	
	@Override
	public void onDestroy(){
		
	}
	
	public class MyBinder extends Binder{
		LocalService getService(){
			return LocalService.this;
		}
	}
	
	// get list 
	public ArrayList<String> getList(){
		return list;
	}
}
