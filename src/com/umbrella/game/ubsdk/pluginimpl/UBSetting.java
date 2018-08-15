package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.demo.plugin.DemoSettingPlugin;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBSettingPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class UBSetting implements IUBSettingPlugin{
	private final String TAG=UBSetting.class.getSimpleName();
	private IUBSettingPlugin mUBSettingPlugin;

	private static UBSetting instance;
	private UBSetting (){}
	public static UBSetting getInstance(){
		if (instance==null) {
			synchronized (UBSetting.class) {
				if (instance == null) {
					instance = new UBSetting();
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
				mUBSettingPlugin=(IUBSettingPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_SETTING);
				if (mUBSettingPlugin==null) {
					UBLogUtil.logE(TAG+"----->no instance of settingPlugin ,instance DemoSettingPlugin instead");
					mUBSettingPlugin=new DemoSettingPlugin(UBSDKConfig.getInstance().getGameActivity());
				}else{
					UBLogUtil.logI(TAG+"----->create settingPlugin success");
				}
				
			}
		});

	}
	
	@Override
	public void gamePause() {
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->gamePause");
				if (mUBSettingPlugin!=null) {
					mUBSettingPlugin.gamePause();
				}else{
					UBLogUtil.logE(TAG+"----->no instance of settingPlugin");
					UBSDK.getInstance().getUBGamePauseCallback().onFailed("no instance of settingPlugin");
				}
				
			}
		});
		
	}

	@Override
	public void exit() {
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->exit");
				if (mUBSettingPlugin!=null) {
					mUBSettingPlugin.exit();
				}else{
					UBLogUtil.logE(TAG+"----->no instance of settingPlugin");
					UBSDK.getInstance().getUBExitCallback().onCancel("no instance of settingPlugin",null);
				}
				
			}
		});
		
	}
	
	
	/*************************************************** 有返回值的方法 ***************************************************/

	@Override
	public int getPlatformID() {
		UBLogUtil.logI(TAG+"----->getPlatformId");
		int platformId=-1;
		if (mUBSettingPlugin!=null) {
			platformId=mUBSettingPlugin.getPlatformID();
		}else{
			UBLogUtil.logE(TAG+"----->no instance of settingPlugin");
		}
		return platformId;
	}

	@Override
	public int getSubPlatformID() {
		UBLogUtil.logI(TAG+"----->getSubPlatformId");
		int subPlatformId=-1;
		if (mUBSettingPlugin!=null) {
			subPlatformId=mUBSettingPlugin.getSubPlatformID();
		}else{
			UBLogUtil.logE(TAG+"----->no instance of settingPlugin");
		}
		return subPlatformId;
	}

	@Override
	public String getExtrasConfig(String extras) {
		UBLogUtil.logI(TAG+"----->getExtrasConfig");
		String configParam="";
		if (mUBSettingPlugin!=null) {
			configParam=mUBSettingPlugin.getExtrasConfig(extras);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of settingPlugin");
		}
		return configParam;
	}

	@Override
	public String getPlatformName() {
		UBLogUtil.logI(TAG+"----->getPlatformName");
		String subPlatformName="";
		if (mUBSettingPlugin!=null) {
			subPlatformName=mUBSettingPlugin.getPlatformName();
		}else{
			UBLogUtil.logE(TAG+"----->no instance of settingPlugin");
		}
		return subPlatformName;
	}

	@Override
	public boolean isSupportMethod(String methodName,Object[] args) {
		UBLogUtil.logI(TAG+"----->isSupportMethod");
		if (mUBSettingPlugin!=null) {
			return mUBSettingPlugin.isSupportMethod(methodName,args);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of settingPlugin");
			return false;
		}
	}
	
	@Override
	public Object callMethod(String methodName, Object[] args) {
		UBLogUtil.logI(TAG+"----->callMethod");
		if (mUBSettingPlugin!=null) {
			return mUBSettingPlugin.callMethod(methodName, args);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of settingPlugin");
			return null;
		}
	}
}
