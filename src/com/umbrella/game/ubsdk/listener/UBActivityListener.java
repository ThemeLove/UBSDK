package com.umbrella.game.ubsdk.listener;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public interface UBActivityListener {
	  public abstract void onApplicationInit(Context paramContext);
	  
	  public void onActivityResult(int requestCode, int resultCode, Intent data);

	  public void onCreate(Bundle savedInstanceState);

	  public void onStart();

	  public void onPause();

	  public void onResume();

	  public void onNewIntent(Intent newIntent);

	  public void onStop();

	  public void onDestroy();

	  public void onRestart();
	  /**
	   * @return false 游戏不需要添加退出逻辑，true 游戏需要调用自己的退出逻辑
	   */
	  public boolean onBackPressed();
	  
	  public void onConfigurationChanged(Configuration newConfig);
}
