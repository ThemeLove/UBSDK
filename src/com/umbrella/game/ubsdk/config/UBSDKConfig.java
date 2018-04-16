package com.umbrella.game.ubsdk.config;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;

public class UBSDKConfig {
	private static UBSDKConfig instance=null;
	
	public static final String UBSDK_CONFIG_FILENAME="ubsdk_config.xml";
    //闪屏图片名称
    public static final String SPLASH_DRAWABLE_NAME = "ubsdk_splash";
    
    public static final String UB_GameID="UB_GameID";
    public static final String UB_GameDebug="UB_GameDebug";
    public static final String UB_GameOrientation="UB_GameOrientation";
    public static final String UB_GameMainActivityName="UB_GameMainActivityName";
    
    public static final String UB_PlatformID="UB_PlatformID";
    public static final String UB_PlatformName="UB_PlatformName";
    public static final String UB_SubPlatformID="UB_SubPlarformID";
    public static final String UB_ChannelID="UB_ChannelID";
    public static final String UB_SubChannelID="UB_SubChannelID";
	
	private UBGame ubGame;
	private UBChannel ubChannel;
	private HashMap<String,String> paramMap;//sdk配置参数集合
	
	private Bundle metaDataBundle;//AndroidManifest.xml元数据集合
	
	private ArrayList<String> applicationList;//代理Application集合
	
	private SparseArray<String> pluginMap;//支持的plugin集合
	
	private UBSDKConfig(){
		ubGame=new UBGame();
		ubChannel=new UBChannel();
		paramMap=new HashMap<>();
		metaDataBundle=new Bundle();
		applicationList=new ArrayList<String>();
		pluginMap=new SparseArray<>();
	}

	public HashMap<String, String> getParamMap() {
		return paramMap;
	}
	
	public Bundle getMetaDataBundle() {
		return metaDataBundle;
	}

	public ArrayList<String> getApplicationList(){
		return applicationList;
	}
	
	public SparseArray<String> getPluginMap( ){
		return pluginMap;
	}
	
	public static UBSDKConfig getInstance(){
		if (instance==null) {
			synchronized (UBSDKConfig.class) {
				if (instance == null) {
					instance = new UBSDKConfig();
				}
			}
		}
		return instance;
	}
	
	private Activity mActivity;
	
	public void setGameActivity(Activity activity){
		this.mActivity=activity;
	}
	
	public Activity getGameActivity( ){
		return mActivity;
	}
	
	private Context mApplicationContext;
	
	public void setApplicationContext(Context context){
		this.mApplicationContext=context;
	}
	
	public Context getApplicationContext(){
		return mApplicationContext;
	}
	
	public UBGame getUBGame(){
		return ubGame;
	}
	
	public UBChannel getUBChannel(){
		return ubChannel;
	}
}
