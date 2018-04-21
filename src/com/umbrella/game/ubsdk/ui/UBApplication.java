package com.umbrella.game.ubsdk.ui;

import java.util.ArrayList;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.listener.IChannelProxyApplication;
import com.umbrella.game.ubsdk.model.UBConfigModel;
import com.umbrella.game.ubsdk.utils.UBLogUtil;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

public class UBApplication extends Application{
	private final String TAG=UBApplication.class.getSimpleName();
	
	private ArrayList<IChannelProxyApplication> channelProxyApplicationList;
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		UBLogUtil.logI(TAG+"----->attachBaseContext");
		
//		添加MuliDex分包支持,单机游戏比较小，可以不添加MulDex支持
		MultiDex.install(this);
		
		UBSDKConfig.getInstance().setApplicationContext(this);
//		加载AndroidManifest.xml中的元数据
		UBConfigModel.getInstance().loadMetaDataBundle(base);
//		TODO 这个地方控制解析加密、不加密文件的flag
		boolean isInitUBSDKConfigSuccess = UBConfigModel.getInstance().initUBSDKConfig(true);
		if (!isInitUBSDKConfigSuccess) {
			UBLogUtil.logW(TAG+"----->waring!!!!!----->load the config file may be fail...");
			UBLogUtil.logW(TAG+"----->waring!!!!!----->init with the demo plugins...");
		}
		
		channelProxyApplicationList = UBConfigModel.getInstance().getUBProxyChannelApplicationList();
		if (channelProxyApplicationList!=null&&channelProxyApplicationList.size()>0){
			for (IChannelProxyApplication channelProxyApplication : channelProxyApplicationList) {
				channelProxyApplication.onProxyAttachBaseContext(this, base);
			}
		} 
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		UBLogUtil.logI(TAG+"----->onCreate");
		
		if (channelProxyApplicationList!=null&&channelProxyApplicationList.size()>0){
			for (IChannelProxyApplication channelProxyApplication : channelProxyApplicationList) {
				channelProxyApplication.onProxyCreate(this);
			}
		} 
		
//		初始化NoHttp
		NoHttp.initialize(this);
		Logger.setDebug(false);
		Logger.setTag("UBSDK");
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		UBLogUtil.logI(TAG+"----->onConfigurationChanged");
		
		if (channelProxyApplicationList!=null&&channelProxyApplicationList.size()>0){
			for (IChannelProxyApplication channelProxyApplication : channelProxyApplicationList) {
				channelProxyApplication.onProxyConfigurationChanged(this, newConfig);
			}
		} 
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		UBLogUtil.logI(TAG+"----->onTerminate");
		
		if (channelProxyApplicationList!=null&&channelProxyApplicationList.size()>0){
			for (IChannelProxyApplication channelProxyApplication : channelProxyApplicationList) {
				channelProxyApplication.onTerminate();
			}
		} 
	}
}
