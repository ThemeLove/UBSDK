package com.umbrella.game.ubsdk.ui;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.listener.IChannelProxyApplication;
import com.umbrella.game.ubsdk.model.UBConfigModel;
import com.umbrella.game.ubsdk.pluginimpl.UBInit;
import com.umbrella.game.ubsdk.pluginimpl.UBPay;
import com.umbrella.game.ubsdk.pluginimpl.UBSetting;
import com.umbrella.game.ubsdk.pluginimpl.UBUser;
import com.umbrella.game.ubsdk.utils.UBLogUtil;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

public class UBApplication extends Application{
	private final String TAG=UBApplication.class.getSimpleName();
	
	private IChannelProxyApplication channelProxyApplication;
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
//		添加MuliDex分包支持
//		MultiDex.install(this),单机游戏比较小，可以不添加MulDex支持
		
		UBSDKConfig.getInstance().setApplicationContext(this);
//		TODO 这个地方控制解析加密、不加密文件的flag
		boolean isInitUBSDKConfigSuccess = UBConfigModel.getInstance().initUBSDKConfig(true);
		if (!isInitUBSDKConfigSuccess) {
			UBLogUtil.logW(TAG+"----->waring!!!!!----->load the config file may be fail...");
			UBLogUtil.logW(TAG+"----->waring!!!!!----->init with the demo plugins...");
		}
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
		
		initPlugin();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (channelProxyApplication!=null) channelProxyApplication.onProxyConfigurationChanged(this, newConfig);
	}
	
	/**
	 * 仅仅实例化插件，并不调用插件里的任何方法
	 */
	private void initPlugin(){
		UBLogUtil.logI(TAG+"----->initPlugin");
		
		UBInit.getInstance().init();
		UBUser.getInstance().init();
		UBPay.getInstance().init();
		UBSetting.getInstance().init();
	}
}
