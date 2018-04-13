package com.umbrella.game.ubsdk.listener;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public interface UBActivityListener {

	  public void onCreate(Bundle savedInstanceState);
	  
	  public void onRestart();

	  public void onStart();

	  public void onPause();

	  public void onResume();

	  /**
	   * Called when the window has been attached to the window manager
	   */
	  public void onAttachedToWindow() ;
	  
	  public void onStop();

	  public void onDestroy();
	  
	  public void onNewIntent(Intent newIntent);
	  
	  public void onBackPressed();
	  
	  public void onConfigurationChanged(Configuration newConfig);
	  
	  public void onActivityResult(int requestCode, int resultCode, Intent data);
	  
	  public void onRequestPermissionResult(int requestCode,String[] permissions, int[] grantResults);
}
