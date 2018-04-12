package com.umbrella.game.ubsdk;

import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;
import com.umbrella.game.ubsdk.callback.UBExitCallback;
import com.umbrella.game.ubsdk.callback.UBGamePauseCallback;
import com.umbrella.game.ubsdk.callback.UBInitCallback;
import com.umbrella.game.ubsdk.callback.UBLoginCallback;
import com.umbrella.game.ubsdk.callback.UBLogoutCallback;
import com.umbrella.game.ubsdk.callback.UBPayCallback;
import com.umbrella.game.ubsdk.callback.UBSwitchAccountCallback;
import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.listener.UBActivityListener;
import com.umbrella.game.ubsdk.pluginimpl.UBPay;
import com.umbrella.game.ubsdk.pluginimpl.UBSetting;
import com.umbrella.game.ubsdk.pluginimpl.UBUser;
import com.umbrella.game.ubsdk.ui.UBDialog;
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
	private UBActivityListener mUBActivityListener;
	public void setUBActivityListener (UBActivityListener ubActivityListener){
		UBLogUtil.logI(TAG+"----->setUBActivityListener");
		mUBActivityListener=ubActivityListener;
	}
	public UBActivityListener getUBActivityListener(){
		return mUBActivityListener;
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
		runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				UBUser.getInstance().init();
				UBPay.getInstance().init();
				UBSetting.getInstance().init();
			}
		});
	}
	
	public void runOnUIThread(Runnable runnable){
		if (runnable==null) return;
		if (mMainThreadHandler!=null) {
			mMainThreadHandler.post(runnable);
		}else{
			mActivity.runOnUiThread(runnable);
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
		if (mUBActivityListener!=null) {
			mUBActivityListener.onCreate(savedInstanceState);
		}
	}
	
	public void onRestart(){
		UBLogUtil.logI(TAG+"----->onRestart");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onRestart();
		}
	}
	
	public void onStart(){
		UBLogUtil.logI(TAG+"----->onStart");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onStart();
		}
	}
	
	public void onResume(){
		UBLogUtil.logI(TAG+"----->onResume");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onResume();
		}
	}
	
	public void onPause(){
		UBLogUtil.logI(TAG+"----->onPause");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onPause();
		}
	}
	
	public void onStop(){
		UBLogUtil.logI(TAG+"----->onStop");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onStop();
		}
	}
	
	public void onDestroy(){
		UBLogUtil.logI(TAG+"----->onDestory");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onDestroy();
		}
	}
	
	public void onNewIntent(Intent intent){
		UBLogUtil.logI(TAG+"----->onNewIntent");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onNewIntent(intent);
		}
	}
	
	public void onBackPressed(){
		UBLogUtil.logI(TAG+"----->onBackPressed");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onBackPressed();
		}
	}
	
	public void onConfigurationChanged(Configuration newConfig){
		UBLogUtil.logI(TAG+"----->onConfigurationChanged");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onConfigurationChanged(newConfig);
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		UBLogUtil.logI(TAG+"----->onActivityResult");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onActivityResult(requestCode,resultCode,data);
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
	 * 是否支持某方法
	 * @param functionName 方法名[functionName]
	 * @return
	 */
	public boolean isFunctionSupported(int functionName){
		UBLogUtil.logI(TAG+"----->isFunctionSupported");
		return UBSetting.getInstance().isFunctionSupported(functionName);
	}
	
	/**
	 * 调用某方法
	 * @param functionName 方法名[functionName]
	 * @return
	 */
	public String callFunction(int functionName){
		UBLogUtil.logI(TAG+"----->callFunction");
		return UBSetting.getInstance().callFunction(functionName);
	}
	
	/**
	 * 设置游戏数据
	 * @param obj
	 * @param dataType 
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
}
