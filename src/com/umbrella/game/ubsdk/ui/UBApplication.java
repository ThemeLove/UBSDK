package com.umbrella.game.ubsdk.ui;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.listener.IChannelProxyApplication;
import com.umbrella.game.ubsdk.model.UBConfigModel;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

public class UBApplication extends Application{
	private IChannelProxyApplication channelProxyApplication;
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
//		添加MuliDex分包支持
		MultiDex.install(this);
		
		UBSDKConfig.getInstance().setApplicationContext(this);
		UBConfigModel.getInstance().initUBSDKConfig(false);
		channelProxyApplication = UBConfigModel.getInstance().getUBProxyChannelApplication();
		if (channelProxyApplication!=null) channelProxyApplication.onProxyAttachBaseContext(this, base);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		if (channelProxyApplication!=null) channelProxyApplication.onProxyCreate(this);
		
//		初始化NoHttp
		NoHttp.initialize(this);
		Logger.setDebug(false);
		Logger.setTag("UBSDK");
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (channelProxyApplication!=null) channelProxyApplication.onProxyConfigurationChanged(this, newConfig);
	}
	

}
