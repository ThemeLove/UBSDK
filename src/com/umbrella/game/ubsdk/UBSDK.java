package com.umbrella.game.ubsdk;

import com.umbrella.game.ubsdk.bean.DataType;
import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;
import com.umbrella.game.ubsdk.callback.UBExitCallback;
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
	private final String TAG=getClass().getSimpleName();
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
//	初始化监听

	private UBInitCallback mUBInitCallback;
	public void setUBInitCallback(UBInitCallback ubInitCallback){
		UBLogUtil.logI(TAG+" setUBInitCallback");
		this.mUBInitCallback=ubInitCallback;
	}
	public UBInitCallback getUBInitCallback(){
		return mUBInitCallback;
	}
	
//	登录监听
	private UBLoginCallback mUBLoginCallback;
	public void setUBLoginCallback(UBLoginCallback ubLoginCallback){
		UBLogUtil.logI(TAG+" setUBLoginCallback");
		this.mUBLoginCallback=ubLoginCallback;
	}
	public UBLoginCallback getUBLoginCallback(){
		return mUBLoginCallback;
	}
	
//	注销监听

	private UBLogoutCallback mUBLogoutCallback;
	public void setUBLogoutCallback(UBLogoutCallback ubLogoutCallback){
		UBLogUtil.logI(TAG+" setUBLogoutCallback");
		this.mUBLogoutCallback=ubLogoutCallback;
	}
	public UBLogoutCallback getUBLogoutCallback(){
		return mUBLogoutCallback;
	}
	

//	切换账号监听

	private UBSwitchAccountCallback mUBSwitchAccountCallback;
	public void setUBSwitchAccountCallback(UBSwitchAccountCallback ubSwitchAccountCallback){
		UBLogUtil.logI(TAG+" setUBSwitchAccountCallback");
		this.mUBSwitchAccountCallback=ubSwitchAccountCallback;
	}
	public UBSwitchAccountCallback getUBSwitchAccountCallback(){
		return mUBSwitchAccountCallback;
	}
	

//	支付监听

	private UBPayCallback mUBPayCallback;
	public void setUBPayCallback(UBPayCallback ubPayCallback){
		this.mUBPayCallback=ubPayCallback;
	}
	public UBPayCallback getUBPayCallback(){
		return mUBPayCallback;
	}
	

//	退出监听

	private UBExitCallback mUBExitCallback;
	public void setUBExitCallback(UBExitCallback ubExitCallback){
		this.mUBExitCallback=ubExitCallback;
	}
	public UBExitCallback getUBExitCallback(){
		return mUBExitCallback;
}
	
//	生命周期监听
	private UBActivityListener mUBActivityListener;
	public void setUBActivityListener (UBActivityListener ubActivityListener){
		UBLogUtil.logI(TAG+" setUBActivityListener");
		mUBActivityListener=ubActivityListener;
	}
	public UBActivityListener getUBActivityListener(){
		return mUBActivityListener;
	}
	
//	TODO**************************init*************************	
	public void init(Activity activity){
		this.mActivity=activity;
		UBLogUtil.logI(TAG+" init");
		
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
	
//	TODO**************************login*************************	
	public void login(){
		UBLogUtil.logI(TAG+" login");
		UBUser.getInstance().login();
	}

//	TODO**************************logout*************************		
	public void logout( ){

		UBLogUtil.logI(TAG+" logout");
		UBUser.getInstance().logout();
	}
	
//	TODO**************************pay*************************	
	public void pay(UBRoleInfo ubRoleInfo,UBOrderInfo ubOrderInfo){
		UBLogUtil.logI(TAG+" pay");
		UBPay.getInstance().pay(ubRoleInfo, ubOrderInfo);
	}
	
//	TODO**************************exit*************************	
	public void exit( ){

		UBLogUtil.logI(TAG+" exit");
		UBSetting.getInstance().exit();
	}
	
//	TODO**************************activity lifecycle*************************

	public void onCreate(Bundle savedInstanceState){
		UBLogUtil.logI(TAG+" onCreate");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onCreate(savedInstanceState);
		}
	}
	
	public void onRestart(){
		UBLogUtil.logI(TAG+" onRestart");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onRestart();
		}
	}
	
	public void onStart(){
		UBLogUtil.logI(TAG+" onStart");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onStart();
		}
	}
	
	public void onResume(){
		UBLogUtil.logI(TAG+" onResume");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onResume();
		}
	}
	
	public void onPause(){
		UBLogUtil.logI(TAG+" onPause");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onPause();
		}
	}
	
	public void onStop(){
		UBLogUtil.logI(TAG+" onStop");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onStop();
		}
	}
	
	public void onDestroy(){
		UBLogUtil.logI(TAG+" onDestory");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onDestroy();
		}
	}
	
	public void onNewIntent(Intent intent){
		UBLogUtil.logI(TAG+" onNewIntent");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onNewIntent(intent);
		}
	}
	
	public void onBackPressed(){
		UBLogUtil.logI(TAG+" onBackPressed");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onBackPressed();
		}
	}
	
	public void onConfigurationChanged(Configuration newConfig){
		UBLogUtil.logI(TAG+" onConfigurationChanged");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onConfigurationChanged(newConfig);
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		UBLogUtil.logI(TAG+" onActivityResult");
		if (mUBActivityListener!=null) {
			mUBActivityListener.onActivityResult(requestCode,resultCode,data);
		}
	}
	
//	TODO**************************other*************************
	public void getUserInfo(){
		UBLogUtil.logI(TAG+" getUserInfo");
		UBUser.getInstance().getUserInfo();
	}
	
	public int getPlatformId(){
		UBLogUtil.logI(TAG+" getPlatformId");
		return UBSetting.getInstance().getPlatformId();
	}
	
	public int getSubPlatformId(){
		UBLogUtil.logI(TAG+" getSubPlatformId");
		return UBSetting.getInstance().getSubPlatformId();
	}
	
	public String getExtrasConfig(String extras){
		UBLogUtil.logI(TAG+" getExtrasConfig");
		return UBSetting.getInstance().getExtrasConfig(extras);
	}
	
	/**
	 * 是否支持某方法
	 * @param functionName 方法名[functionName]
	 * @return
	 */
	public boolean isFunctionSupported(int functionName){
		UBLogUtil.logI(TAG+" isFunctionSupported");
		return UBSetting.getInstance().isFunctionSupported(functionName);
	}
	
	/**
	 * 调用某方法
	 * @param functionName 方法名[functionName]
	 * @return
	 */
	public String callFunction(int functionName){
		UBLogUtil.logI(TAG+" callFunction");
		return UBSetting.getInstance().callFunction(functionName);
	}
	
	/**
	 * 设置游戏数据
	 * @param obj
	 * @param dataType 
	 */
	public void setGameDataInfo(Object obj,DataType dataType){
		UBLogUtil.logI(TAG+" setGameDataInfo");
		UBUser.getInstance().setGameDataInfo(obj, dataType);
	}
	

	/**
	 * 自带退出框
	 */
    public void showExitDialog()
    {
    	UBDialog.showExitDialog(mActivity);
    }
}
