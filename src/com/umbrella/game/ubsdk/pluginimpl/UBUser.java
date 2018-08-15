package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.demo.plugin.DemoUserPlugin;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBUserPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.plugintype.user.UBUserInfo;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class UBUser implements IUBUserPlugin{
	private final String TAG=UBUser.class.getSimpleName();
	private IUBUserPlugin mUBUserPlugin=null;
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
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->init");
				mUBUserPlugin=(IUBUserPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_USER);
				if (mUBUserPlugin==null) {
					UBLogUtil.logE(TAG+"----->no instance of userPlugin ,instance DemoUserPlugin instead");
					mUBUserPlugin=new DemoUserPlugin(UBSDKConfig.getInstance().getGameActivity());
				}else{
					UBLogUtil.logI(TAG+"----->create userPlugin success");
				}
				
			}
		});
	}

	@Override
	public void login() {
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->login");
				
				if (mUBUserPlugin!=null) {
					mUBUserPlugin.login();
				}else{
					UBLogUtil.logE(TAG+"----->no instance of userPlugin");
					UBSDK.getInstance().getUBLoginCallback().onFailed("no instance of userPlugin", null);
				}
				
			}
		});
	}

	@Override
	public void logout() {
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->logout");
				if (mUBUserPlugin!=null) {
					mUBUserPlugin.logout();
				}else{
					UBLogUtil.logE(TAG+"----->no instance of userPlugin");
					UBSDK.getInstance().getUBLogoutCallback().onFailed("no instance of userPlugin", null);
				}
			}
		});
		
	}

	@Override
	public void setGameDataInfo(final Object obj,final int dataType) {
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->setGameDataInfo");	
				if (mUBUserPlugin!=null) {
					mUBUserPlugin.setGameDataInfo(obj, dataType);
				}else{
					UBLogUtil.logE(TAG+"----->no instance of userPlugin");
				}
				
			}
		});
		
	}
	
	/*************************************************** 有返回值的方法 ***************************************************/
	@Override
	public UBUserInfo getUserInfo() {
		
		UBLogUtil.logI(TAG+"----->getUserInfo");
		if (mUBUserPlugin!=null) {
			return mUBUserPlugin.getUserInfo();
		}else{
			UBLogUtil.logE(TAG+"----->no instance of userPlugin");
			return null;
		}
	}
	
	@Override
	public boolean isSupportMethod(final String methodName,final Object[] args) {
		
		UBLogUtil.logI(TAG+"----->isSupportMethod");
		if (mUBUserPlugin!=null) {
			return mUBUserPlugin.isSupportMethod(methodName, args);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of userPlugin");
			return false;
		}
		
	}

	@Override
	public Object callMethod(String methodName, Object[] args) {
		
		UBLogUtil.logI(TAG+"----->callMethod");
		if (mUBUserPlugin!=null) {
			return mUBUserPlugin.isSupportMethod(methodName, args);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of userPlugin");
			return null;
		}
	}
}
