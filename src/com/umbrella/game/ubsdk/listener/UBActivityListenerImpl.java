package com.umbrella.game.ubsdk.listener;
import com.umbrella.game.ubsdk.utils.UBLogUtil;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class UBActivityListenerImpl implements UBActivityListener{
	private final String TAG=getClass().getSimpleName(); 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		UBLogUtil.logI(TAG,"onCreate");
	}

	@Override
	public void onRestart() {
		UBLogUtil.logI(TAG,"onRestart");
	}

	@Override
	public void onStart() {
		UBLogUtil.logI(TAG,"onStart");
	}

	@Override
	public void onPause() {
		UBLogUtil.logI(TAG,"onPause");
	}

	@Override
	public void onResume() {
		UBLogUtil.logI(TAG,"onResume");
	}
	
	@Override
	public void onAttachedToWindow() {
		UBLogUtil.logI(TAG,"onAttachedToWindow");
	}

	@Override
	public void onStop() {
		UBLogUtil.logI(TAG,"onStop");
	}

	@Override
	public void onDestroy() {
		UBLogUtil.logI(TAG,"onDestroy");
	}

	
	@Override
	public void onNewIntent(Intent newIntent) {
		UBLogUtil.logI(TAG,"onNewIntent");
	}

	@Override
	public void onBackPressed() {
		UBLogUtil.logI(TAG,"onBackPressed");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		UBLogUtil.logI(TAG,"onConfigurationChanged");
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		UBLogUtil.logI(TAG,"onActivityResult");
	}
	
}
