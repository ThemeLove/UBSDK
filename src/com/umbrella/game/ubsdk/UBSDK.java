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
import com.umbrella.game.ubsdk.listener.UBActivityListener;
import com.umbrella.game.ubsdk.pluginimpl.UBPay;
import com.umbrella.game.ubsdk.pluginimpl.UBSetting;
import com.umbrella.game.ubsdk.pluginimpl.UBUser;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
	
	public void init(Activity activity){
		UBLogUtil.logI(TAG+" init");
		this.mActivity=activity;
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
//	**************************setListener*************************	
	private UBInitCallback mUBInitCallback;
	public void setUBInitCallback(UBInitCallback ubInitCallback){
		UBLogUtil.logI(TAG+" setUBInitCallback");
		this.mUBInitCallback=ubInitCallback;
	}
	public UBInitCallback getUBInitCallback(){
		return mUBInitCallback;
	}
	
	private UBLoginCallback mUBLoginCallback;
	public void setUBLoginCallback(UBLoginCallback ubLoginCallback){
		UBLogUtil.logI(TAG+" setUBLoginCallback");
		this.mUBLoginCallback=ubLoginCallback;
	}
	public UBLoginCallback getUBLoginCallback(){
		return mUBLoginCallback;
	}
	
	private UBLogoutCallback mUBLogoutCallback;
	public void setUBLogoutCallback(UBLogoutCallback ubLogoutCallback){
		UBLogUtil.logI(TAG+" setUBLogoutCallback");
		this.mUBLogoutCallback=ubLogoutCallback;
	}
	public UBLogoutCallback getUBLogoutCallback(){
		return mUBLogoutCallback;
	}
	
	private UBSwitchAccountCallback mUBSwitchAccountCallback;
	public void setUBSwitchAccountCallback(UBSwitchAccountCallback ubSwitchAccountCallback){
		UBLogUtil.logI(TAG+" setUBSwitchAccountCallback");
		this.mUBSwitchAccountCallback=ubSwitchAccountCallback;
	}
	public UBSwitchAccountCallback getUBSwitchAccountCallback(){
		return mUBSwitchAccountCallback;
	}
	
	private UBPayCallback mUBPayCallback;
	public void setUBPayCallback(UBPayCallback ubPayCallback){
		this.mUBPayCallback=ubPayCallback;
	}
	public UBPayCallback getUBPayCallback(){
		return mUBPayCallback;
	}
	
	private UBExitCallback mUBExitCallback;
	public void setUBExitCallback(UBExitCallback ubExitCallback){
		this.mUBExitCallback=ubExitCallback;
	}
	public UBExitCallback getUBExitCallback(){
		return mUBExitCallback;
	}
	
//	**************************user*************************	
	public void login(){
		UBLogUtil.logI(TAG+" login");
		UBUser.getInstance().login();
	}
	
	public void logout(){
		UBLogUtil.logI(TAG+" logout");
		UBUser.getInstance().logout();
	}
	
	public void getUserInfo(){
		UBLogUtil.logI(TAG+" getUserInfo");
		UBUser.getInstance().getUserInfo();
	}
	
	public void setGameDataInfo(Object obj,DataType dataType){
		UBLogUtil.logI(TAG+" setGameDataInfo");
		UBUser.getInstance().setGameDataInfo(obj, dataType);
	}
	
//	**************************pay*************************
	public void pay(UBRoleInfo ubRoleInfo,UBOrderInfo ubOrderInfo){
		UBLogUtil.logI(TAG+" pay");
		UBPay.getInstance().pay(ubRoleInfo, ubOrderInfo);
	}
	
	
//	**************************setting*************************
	public void exit(){
		UBLogUtil.logI(TAG+" exit");
		UBSetting.getInstance().exit();
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
	
	public boolean isFunctionSupported(int functionName){
		UBLogUtil.logI(TAG+" isFunctionSupported");
		return UBSetting.getInstance().isFunctionSupported(functionName);
	}
	
	public String callFunction(int functionName){
		UBLogUtil.logI(TAG+" callFunction");
		return UBSetting.getInstance().callFunction(functionName);
	}
//	**************************activity lifecycle*************************
	private UBActivityListener mUBActivityListener;
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
	
//	**************************other*************************
	/**
	 * 自带退出框
	 */
    public void showExitDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity).setTitle("退出游戏").setMessage("您确定要退出游戏么?").setPositiveButton(
                "确定", new OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mActivity.finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                }).setNegativeButton("取消", new OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
