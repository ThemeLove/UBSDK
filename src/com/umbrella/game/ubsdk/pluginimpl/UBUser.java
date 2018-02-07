package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.bean.DataType;
import com.umbrella.game.ubsdk.bean.UBUserInfo;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBUserPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class UBUser implements IUBUserPlugin{
	private final String TAG=getClass().getSimpleName();
	private IUBUserPlugin mUBUserPlugin;
	private static UBUser instance;
	private UBUser(){}
	
	public static UBUser getInstance(){
		if (instance==null) {
			synchronized (UBUser.class) {
				if (instance == null) {
					instance = new UBUser();
				}
			}
		}
		return instance;
	}
	
	public void init(){
		UBLogUtil.logI(TAG+" init");
		
		mUBUserPlugin=(IUBUserPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_USER.getPluginType());
		
		if (mUBUserPlugin==null) {
			UBLogUtil.logE("no instance of userPlugin");
		}else{
			UBLogUtil.logI("create userPlugin success");
		}
	}

	@Override
	public void login() {
		UBLogUtil.logI(TAG+" login");
		
		if (mUBUserPlugin!=null){
			UBSDK.getInstance().runOnUIThread(new Runnable() {
				
				@Override
				public void run() {
					mUBUserPlugin.login();
				}
			});
		}else{
			UBLogUtil.logE("no instance of userPlugin");
		}
	}

	@Override
	public void logout() {
		UBLogUtil.logI(TAG+" logout");
		if (mUBUserPlugin!=null){
			UBSDK.getInstance().runOnUIThread(new Runnable() {
				
				@Override
				public void run() {
					mUBUserPlugin.logout();
				}
			});
		}else{
			UBLogUtil.logE("no instance of userPlugin");
		} 
	}

	@Override
	public UBUserInfo getUserInfo() {
		UBLogUtil.logI(TAG+" getUserInfo");
		if (mUBUserPlugin!=null) {
			return mUBUserPlugin.getUserInfo();
		}else{
			UBLogUtil.logE("no instance of userPlugin");
			return null;
		}
	}

	@Override
	public void setGameDataInfo(Object obj, DataType dataType) {
		UBLogUtil.logI(TAG+" setGameDataInfo");
		if (mUBUserPlugin!=null) {
			mUBUserPlugin.setGameDataInfo(obj,dataType);
		}else{
			UBLogUtil.logE("no instance of userPlugin");
		}
	}
}
