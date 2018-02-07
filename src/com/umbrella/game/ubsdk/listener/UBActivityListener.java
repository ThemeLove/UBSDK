package com.umbrella.game.ubsdk.listener;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public interface UBActivityListener {
//	  public abstract void onApplicationInit(Context paramContext);
	  
	  public void onActivityResult(int requestCode, int resultCode, Intent data);

	  public void onCreate(Bundle savedInstanceState);
	  
	  public void onRestart();

	  public void onStart();

	  public void onPause();

	  public void onResume();

	  public void onStop();

	  public void onDestroy();
	  
	  public void onNewIntent(Intent newIntent);
	  
	  public void onBackPressed();
	  
	  public void onConfigurationChanged(Configuration newConfig);
}
