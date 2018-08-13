package com.umbrella.game.ubsdk;

import java.util.ArrayList;

import com.umbrella.game.ubsdk.callback.UBExitCallback;
import com.umbrella.game.ubsdk.callback.UBGamePauseCallback;
import com.umbrella.game.ubsdk.callback.UBInitCallback;
import com.umbrella.game.ubsdk.callback.UBLoginCallback;
import com.umbrella.game.ubsdk.callback.UBLogoutCallback;
import com.umbrella.game.ubsdk.callback.UBPayCallback;
import com.umbrella.game.ubsdk.callback.UBSwitchAccountCallback;
import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.listener.UBActivityListener;
import com.umbrella.game.ubsdk.pluginimpl.UBAD;
import com.umbrella.game.ubsdk.pluginimpl.UBPay;
import com.umbrella.game.ubsdk.pluginimpl.UBSetting;
import com.umbrella.game.ubsdk.pluginimpl.UBUser;
import com.umbrella.game.ubsdk.plugintype.pay.UBOrderInfo;
import com.umbrella.game.ubsdk.plugintype.user.UBRoleInfo;
import com.umbrella.game.ubsdk.ui.UBDialog;
import com.umbrella.game.ubsdk.utils.DisplayUtil;
import com.umbrella.game.ubsdk.utils.TextUtil;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class UBSDK {
	private final String TAG=UBSDK.class.getSimpleName();
	private static UBSDK instance=null;
	private Handler mMainThreadHandler=null;
	private UBSDK(){
		mMainThreadHandler=new Handler(Looper.getMainLooper());
	};
	public static UBSDK getInstance(){
		if (instance==null) {
			synchronized (UBSDK.class) {
				if (instance == null) {
					instance = new UBSDK();
				}
			}
		}
		return instance;
	}
	
	private Activity mActivity;
	
//	TODO**************************setListener*************************	
//	生命周期监听
	private ArrayList<UBActivityListener> mUBActivityListenerList=new ArrayList<UBActivityListener>();
	public void setUBActivityListener (UBActivityListener ubActivityListener){
		UBLogUtil.logI(TAG+"----->setUBActivityListener");
		if (!mUBActivityListenerList.contains(ubActivityListener)) {
			mUBActivityListenerList.add(ubActivityListener);
		}
	}
	
//	切换账号监听
	private UBSwitchAccountCallback mUBSwitchAccountCallback;
	public void setUBSwitchAccountCallback(UBSwitchAccountCallback ubSwitchAccountCallback){
		UBLogUtil.logI(TAG+"----->setUBSwitchAccountCallback");
		this.mUBSwitchAccountCallback=ubSwitchAccountCallback;
	}
	public UBSwitchAccountCallback getUBSwitchAccountCallback(){
		return mUBSwitchAccountCallback;
	}
	
//	TODO**************************init*************************	
	private UBInitCallback mUBInitCallback;
	public UBInitCallback getUBInitCallback(){
		return mUBInitCallback;
	}
	
	public void init(Activity activity,UBInitCallback ubInitCallback){
		this.mActivity=activity;
		this.mUBInitCallback=ubInitCallback;
		
		UBLogUtil.logI(TAG+"----->init");
		UBLogUtil.logI(TAG+"----->setUBInitCallback");
		
		UBSDKConfig.getInstance().setGameActivity(activity);
		
//		初始化屏幕工具类
		DisplayUtil.initScreen(activity);
		
		runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				UBUser.getInstance().init();
				UBPay.getInstance().init();
				UBSetting.getInstance().init();
				UBAD.getInstance().init();
			}
		});
	}
	
	/**
	 * 在主线程执行指定任务
	 * @param runnable  任务
	 */
	public void runOnUIThread(Runnable runnable){
		if (runnable==null) return;
		if (mMainThreadHandler!=null) {
			mMainThreadHandler.post(runnable);
		}else{
			mActivity.runOnUiThread(runnable);
		}
	}
	
	/**
	 * 延迟指定毫秒数在主线程执行指定任务
	 * @param runnable	任务
	 * @param delayMillis	延迟毫秒数
	 */
	public void runOnUIThread(Runnable runnable,long delayMillis){
		if (runnable==null) return;
		if (mMainThreadHandler!=null) {
			mMainThreadHandler.postDelayed(runnable, delayMillis);
		}else{
			new Handler(mActivity.getMainLooper()).postDelayed(runnable, delayMillis);
		}	
	}
	
//	TODO**************************gamePause*************************		
	private UBGamePauseCallback mUBGamePauseCallback;
	public UBGamePauseCallback getUBGamePauseCallback(){
		return mUBGamePauseCallback;
	}
	
	public void gamePause(UBGamePauseCallback ubGamePauseCallback){
		this.mUBGamePauseCallback=ubGamePauseCallback;
		UBLogUtil.logI(TAG+"----->gamePause");
		UBLogUtil.logI(TAG+"----->setUBGamePauseCallback");
		
		UBSetting.getInstance().gamePause();
	}
	
//	TODO**************************login*************************	
	private UBLoginCallback mUBLoginCallback;
	public UBLoginCallback getUBLoginCallback(){
		return mUBLoginCallback;
	}
	
	public void login(UBLoginCallback ubLoginCallback){
		this.mUBLoginCallback=ubLoginCallback;
		
		UBLogUtil.logI(TAG+"----->login");
		UBLogUtil.logI(TAG+"----->setUBLoginCallback");
		
		UBUser.getInstance().login();
	}
	
//	TODO**************************logout*************************		
	private UBLogoutCallback mUBLogoutCallback;
	public UBLogoutCallback getUBLogoutCallback(){
		return mUBLogoutCallback;
	}
	
	public void logout(UBLogoutCallback ubLogoutCallback){
		UBLogUtil.logI(TAG+"----->logout");
		UBLogUtil.logI(TAG+"----->setUBLogoutCallback");
		
		this.mUBLogoutCallback=ubLogoutCallback;
		UBUser.getInstance().logout();
	}
	
//	TODO**************************pay*************************	
	private UBPayCallback mUBPayCallback;
	public UBPayCallback getUBPayCallback(){
		return mUBPayCallback;
	}
	public void pay(UBRoleInfo ubRoleInfo,UBOrderInfo ubOrderInfo,UBPayCallback ubPayCallback){
		this.mUBPayCallback=ubPayCallback;
		
		UBLogUtil.logI(TAG+"----->pay");
		UBLogUtil.logI(TAG+"----->setUBPayCallback");
		
		UBPay.getInstance().pay(ubRoleInfo, ubOrderInfo);
	}
	
//	TODO**************************exit*************************	
	private UBExitCallback mUBExitCallback;
	public UBExitCallback getUBExitCallback(){
		return mUBExitCallback;
	}
	public void exit(UBExitCallback ubExitCallback){
		this.mUBExitCallback=ubExitCallback;
		
		UBLogUtil.logI(TAG+"----->exit");
		UBLogUtil.logI(TAG+"----->setUBExitCallback");
		
		UBSetting.getInstance().exit();
	}
	
//	TODO**************************activity lifecycle*************************
	public void onCreate(Bundle savedInstanceState){
		UBLogUtil.logI(TAG+"----->onCreate");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onCreate(savedInstanceState);
			}
		}
	}
	
	public void onRestart(){
		UBLogUtil.logI(TAG+"----->onRestart");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onRestart();
			}
		}
	}
	
	public void onStart(){
		UBLogUtil.logI(TAG+"----->onStart");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onStart();
			}
		}
	}
	
	public void onResume(){
		UBLogUtil.logI(TAG+"----->onResume");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onResume();
			}
		}
	}
	
	/**
	* Called when the window has been attached to the window manager
	*/
	public void onAttachedToWindow(){
		UBLogUtil.logI(TAG+"----->onAttachedToWindow");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onAttachedToWindow();
			}
		}
	}
	
	public void onPause(){
		UBLogUtil.logI(TAG+"----->onPause");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onPause();
			}
		}
	}
	
	public void onStop(){
		UBLogUtil.logI(TAG+"----->onStop");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onStop();
			}
		}
	}
	
	public void onDestroy(){
		UBLogUtil.logI(TAG+"----->onDestory");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onDestroy();
			}
		}
	}
	
	public void onNewIntent(Intent intent){
		UBLogUtil.logI(TAG+"----->onNewIntent");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onNewIntent(intent);
			}
		}
	}
	
	public void onBackPressed(){
		UBLogUtil.logI(TAG+"----->onBackPressed");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onBackPressed();
			}
		}
	}
	
	public void onConfigurationChanged(Configuration newConfig){
		UBLogUtil.logI(TAG+"----->onConfigurationChanged");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onConfigurationChanged(newConfig);
			}
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		UBLogUtil.logI(TAG+"----->onActivityResult");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onActivityResult(requestCode,resultCode,data);
			}
		}
	}
	
	public void onRequestPermissionResult(int requestCode,String[] permissions, int[] grantResults) {
		UBLogUtil.logI(TAG+"----->onRequestPermissionResult");
		if (mUBActivityListenerList!=null&&mUBActivityListenerList.size()>0) {
			for (UBActivityListener ubActivityListener : mUBActivityListenerList) {
				ubActivityListener.onRequestPermissionResult(requestCode,permissions,grantResults);
			}
		}
	}
	
//	TODO**************************other*************************
	public void getUserInfo(){
		UBLogUtil.logI(TAG+"----->getUserInfo");
		UBUser.getInstance().getUserInfo();
	}
	
	public int getPlatformID(){
		UBLogUtil.logI(TAG+"----->getPlatformID");
		return UBSetting.getInstance().getPlatformID();
	}
	
	public String getPlatformName(){
		UBLogUtil.logI(TAG+"----->getPlatformName");
		return UBSetting.getInstance().getPlatformName();
	}
	
	public int getSubPlatformID(){
		UBLogUtil.logI(TAG+"----->getSubPlatformID");
		return UBSetting.getInstance().getSubPlatformID();
	}
	
	public String getExtrasConfig(String extras){
		UBLogUtil.logI(TAG+"----->getExtrasConfig");
		return UBSetting.getInstance().getExtrasConfig(extras);
	}
	
	/**
	 * 设置游戏数据
	 * @param obj
	 * @param dataType 		DataType.CREATE_ROLE	 DataType.ENTER_GAME	 DataType.LEVEL_UP
	 */
	public void setGameDataInfo(Object obj,int dataType){
		UBLogUtil.logI(TAG+"----->setGameDataInfo");
		UBUser.getInstance().setGameDataInfo(obj, dataType);
	}
	
	/**
	 * 自带退出框
	 */
    public void showExitDialog()
    {
		UBLogUtil.logI(TAG+"----->showExitDialog");
    	UBDialog.showExitDialog(mActivity);
    }
    
    /**
     * 是否支持该种类型的插件
     * @param pluginType 
     * @return
     */
	public boolean isSupportPlugin(int pluginType){
		UBLogUtil.logI(TAG+"----->isSupportPlugin");
		String pluginName = UBSDKConfig.getInstance().getPluginMap().get(pluginType);
		return !TextUtil.isEmpty(pluginName)?true:false;
	}
    
    /**
     * 根据插件类型判断是否支持某方法
     * @param pluginType
     * @param methodName
     * @param args
     * @return
     */
    public boolean isSupportMethod(int pluginType,String methodName,Object[] args){
    	UBLogUtil.logI(TAG+"----->isSupportMethod");
    	boolean isSupportMethod=false;
    	switch (pluginType) {
		case PluginType.PLUGIN_TYPE_USER:
			isSupportMethod = UBUser.getInstance().isSupportMethod(methodName, args);
			break;
		case PluginType.PLUGIN_TYPE_PAY:
			isSupportMethod = UBPay.getInstance().isSupportMethod(methodName, args);
			break;
		case PluginType.PLUGIN_TYPE_SETTING:
			isSupportMethod = UBSetting.getInstance().isSupportMethod(methodName, args);
			break;
		case PluginType.PLUGIN_TYPE_AD:
			isSupportMethod = UBAD.getInstance().isSupportMethod(methodName, args);
			break;
		case PluginType.PLUGIN_TYPE_PUSH:
			
			break;
		case PluginType.PLUGIN_TYPE_ANALYTICS:
			
			break;
		case PluginType.PLUGIN_TYPE_CRASH:
			
			break;
		default:
			UBLogUtil.logI(TAG+"----->no such type of plugin");
			break;
		}
    	
    	return isSupportMethod;
    }
    
    /**
     * 根据插件类型调用某方法
     * @param pluginType
     * @param methodName
     * @param args
     * @return
     */
    public Object callMethod(int pluginType,String methodName,Object[] args){
    	UBLogUtil.logI(TAG+"----->callMethod");
    	Object obj=null;
    	switch (pluginType) {
		case PluginType.PLUGIN_TYPE_USER:
			obj = UBUser.getInstance().callMethod(methodName, args);
			break;
		case PluginType.PLUGIN_TYPE_PAY:
			obj = UBPay.getInstance().callMethod(methodName, args);
			break;
		case PluginType.PLUGIN_TYPE_SETTING:
			obj = UBSetting.getInstance().callMethod(methodName, args);
			break;
		case PluginType.PLUGIN_TYPE_AD:
			obj = UBAD.getInstance().callMethod(methodName, args);
			break;
		case PluginType.PLUGIN_TYPE_PUSH:
			
			break;
		case PluginType.PLUGIN_TYPE_ANALYTICS:
			
			break;
		case PluginType.PLUGIN_TYPE_CRASH:
			
			break;
		default:
			UBLogUtil.logI(TAG+"----->no such type of plugin");
			break;
		}
    	return obj;
    }
    
}
