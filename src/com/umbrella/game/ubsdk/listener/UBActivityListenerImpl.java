package com.umbrella.game.ubsdk.listener;
import com.umbrella.game.ubsdk.utils.UBLogUtil;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class UBActivityListenerImpl implements UBActivityListener{
	private final String TAG=getClass().getSimpleName(); 
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onActivityResult");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onCreate");
	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onRestart");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onStart");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onPause");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onResume");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onStop");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onDestroy");
	}

	@Override
	public void onNewIntent(Intent newIntent) {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onNewIntent");
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onBackPressed");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		UBLogUtil.logI(TAG,"onConfigurationChanged");
	}
	
}
