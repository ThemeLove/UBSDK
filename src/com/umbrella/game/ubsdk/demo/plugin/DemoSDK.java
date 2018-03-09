package com.umbrella.game.ubsdk.demo.plugin;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.bean.DataType;
import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;
import com.umbrella.game.ubsdk.bean.UBUserInfo;
import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.listener.UBActivityListenerImpl;
import com.umbrella.game.ubsdk.ui.UBDialog;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;
import android.content.DialogInterface;

public class DemoSDK {
	private final String TAG=DemoSDK.class.getSimpleName();
	private static DemoSDK instance;
	private DemoSDK(){}
	public static DemoSDK getInstance(){
		if (instance==null) {
			synchronized (DemoSDK.class) {
				if (instance == null) {
					instance = new DemoSDK();
				}
			}
		}
		return instance;
	}
	
	private Activity mActivity;
	
	public void init(){
		UBLogUtil.logI(TAG+"----->init");
		mActivity=UBSDKConfig.getInstance().getGameActivity();
		UBSDK.getInstance().setUBActivityListener(new UBActivityListenerImpl(){
			@Override
			public void onBackPressed() {
				super.onBackPressed();
			}
		});
		
//		成功回调
		UBSDK.getInstance().getUBInitCallback().onSuccess();
	}
	
	public void logout(){
		UBLogUtil.logI(TAG+"----->logout");
		UBDialog.showNormalDialog(mActivity, "logout", "Simulate store logout", "success", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				注销成功回调
				UBSDK.getInstance().getUBLogoutCallback().onSuccess();
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		}, "fail", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UBSDK.getInstance().getUBLogoutCallback().onFailed("logout fail",null);
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		});
	}
	
	public void gamePause(){
		UBLogUtil.logI(TAG+"----->gamePause");
		UBSDK.getInstance().getUBGamePauseCallback().onGamePause();
	}
	
	public void exit(){
		UBLogUtil.logI(TAG+"----->exit");
		UBDialog.showNormalDialog(mActivity, "exit the game", "Are you sure you want to quit?", "exit", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UBSDK.getInstance().getUBExitCallback().onExit();
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		}, "cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UBSDK.getInstance().getUBExitCallback().onCancel("user cancel",null);
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		});
	}
	
	private UBUserInfo mUBUserInfo;
	public UBUserInfo getUserInfo(){
		UBLogUtil.logI(TAG+"----->getUserInfo");
		return mUBUserInfo;
	}
	
	public void login(){
		UBLogUtil.logI(TAG+"----->login");
		UBDialog.showNormalDialog(mActivity, "login", "Simulate store login", "success",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UBUserInfo ubUserInfo = new UBUserInfo();
				ubUserInfo.setUid("123456");
				ubUserInfo.setUserName("ubsdktest");
				ubUserInfo.setToken("123456ABCDEFG");
				ubUserInfo.setExtra("extra");
				
				mUBUserInfo=ubUserInfo;
				
//				成功回调
				UBSDK.getInstance().getUBLoginCallback().onSuccess(ubUserInfo);
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		}, "fail",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				失败回调
				UBSDK.getInstance().getUBLoginCallback().onFailed("wrong password", null);
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		});
	}
	
	public void pay(final UBRoleInfo ubRoleInfo,final UBOrderInfo ubOrderInfo){
		UBLogUtil.logI(TAG+"----->pay");
		UBDialog.showNormalDialog(mActivity, "pay", "Simulate store payment", "success", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				成功回调
				UBSDK.getInstance().getUBPayCallback().onSuccess(ubOrderInfo.getCpOrderID(),ubOrderInfo.getOrderID(), ubOrderInfo.getGoodsID(),ubOrderInfo.getGoodsName(),ubOrderInfo.getAmount()+"", ubOrderInfo.getExtrasParams());
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		}, "fail", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				失败回调
				UBSDK.getInstance().getUBPayCallback().onFailed(ubOrderInfo.getCpOrderID(), "parameter error", null);
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		});
	}
	
	public void setGameDataInfo(Object obj,int dataType){
		
		switch (dataType) {
		case DataType.CREATE_ROLE:
			UBLogUtil.logI(TAG+"----->create_role");
			break;
		case DataType.ENTER_GAME:
			UBLogUtil.logI(TAG+"----->enter_game");
			break;
		case DataType.LEVEL_UP:
			UBLogUtil.logI(TAG+"----->level_up");
			break;
		default:
			break;
		}
	}

}
