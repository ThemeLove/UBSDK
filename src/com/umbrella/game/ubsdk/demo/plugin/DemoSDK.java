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
	private final String TAG=getClass().getSimpleName();
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
		mActivity=UBSDKConfig.getInstance().getGameActivity();
		UBSDK.getInstance().setUBActivityListener(new UBActivityListenerImpl(){
			@Override
			public void onBackPressed() {
				super.onBackPressed();
				exit();
			}
		});
		
//		成功回调
		UBSDK.getInstance().getUBInitCallback().onSuccess();
	}
	
	public void logout(){
		UBDialog.showNormalDialog(mActivity, "注销", "模拟注销", "注销成功", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				注销成功回调
				UBSDK.getInstance().getUBLogoutCallback().onSuccess();
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		}, "注销失败", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UBSDK.getInstance().getUBLogoutCallback().onFailed("logout fail",null);
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		});
	}
	
	public void exit(){
		UBDialog.showNormalDialog(mActivity, "退出游戏", "确定退出吗？", "退出", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UBSDK.getInstance().getUBExitCallback().onExit();
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		}, "取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				UBSDK.getInstance().getUBExitCallback().onCancel("取消",null);
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		});
	}
	
	private UBUserInfo mUBUserInfo;
	public UBUserInfo getUserInfo(){
		return mUBUserInfo;
	}
	
	public void login(){
		UBDialog.showNormalDialog(mActivity, "登录", "模拟渠道sdk登录", "登录成功",new DialogInterface.OnClickListener() {
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
		}, "登录失败",new DialogInterface.OnClickListener() {
			
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
		UBDialog.showNormalDialog(mActivity, "支付", "模拟渠道sdk支付", "支付成功", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				成功回调
				UBSDK.getInstance().getUBPayCallback().onSuccess(ubOrderInfo.getOrderId(), ubOrderInfo.getCpOrderId(), ubOrderInfo.getExtrasParams());
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		}, "支付失败", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				失败回调
				UBSDK.getInstance().getUBPayCallback().onFailed(ubOrderInfo.getCpOrderId(), "parameter error", null);
				
//				隐藏dialog
				dialog.dismiss();
				dialog=null;
			}
		});
	}
	
	public void setGameDataInfo(Object obj,DataType dataType){
		switch (dataType) {
		case CREATE_ROLE:
			UBLogUtil.logI(TAG, "create_role");
			break;
		case ENTER_GAME:
			UBLogUtil.logI(TAG, "enter_game");
			break;
		case LEVEL_UP:
			UBLogUtil.logI(TAG, "level_up");
			break;
		default:
			break;
		}
	}

}
