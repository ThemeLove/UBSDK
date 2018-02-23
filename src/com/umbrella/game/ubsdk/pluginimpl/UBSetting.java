package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBSettingPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class UBSetting implements IUBSettingPlugin{
	private final String TAG=getClass().getSimpleName();
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
		UBLogUtil.logI(TAG+" init");
		
		mUBSettingPlugin=(IUBSettingPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_SETTING.getPluginType());
		if (mUBSettingPlugin!=null) {
			UBLogUtil.logI("create settingPlugin success");
		}else{
			UBLogUtil.logE("no instance of settingPlugin");
		}
	}

	@Override
	public void exit() {
		UBLogUtil.logI(TAG+" exit");
		if (mUBSettingPlugin!=null) {
			UBSDK.getInstance().runOnUIThread(new Runnable() {
				
				@Override
				public void run() {
					mUBSettingPlugin.exit();
				}
			});
		}else{
			UBLogUtil.logE("no instance of settingPlugin");
		}
	}

	@Override
	public int getPlatformId() {
		UBLogUtil.logI(TAG+" getPlatformId");
		int platformId=-1;
		if (mUBSettingPlugin!=null) {
			platformId=mUBSettingPlugin.getPlatformId();
		}else{
			UBLogUtil.logE("no instance of settingPlugin");
		}
		return platformId;
	}

	@Override
	public int getSubPlatformId() {
		UBLogUtil.logI(TAG+" getSubPlatformId");
		int subPlatformId=-1;
		if (mUBSettingPlugin!=null) {
			subPlatformId=mUBSettingPlugin.getSubPlatformId();
		}else{
			UBLogUtil.logE("no instance of settingPlugin");
		}
		return subPlatformId;
	}

	@Override
	public String getExtrasConfig(String extras) {
		UBLogUtil.logI(TAG+" getExtrasConfig");
		String configParam="";
		if (mUBSettingPlugin!=null) {
			configParam=mUBSettingPlugin.getExtrasConfig(extras);
		}else{
			UBLogUtil.logE("no instance of settingPlugin");
		}
		return configParam;
	}

	@Override
	public boolean isFunctionSupported(int functionName) {
		UBLogUtil.logI(TAG+" isFunctionSupported");
		boolean isSupport=false;
		if (mUBSettingPlugin!=null) {
			isSupport=mUBSettingPlugin.isFunctionSupported(functionName);
		}else{
			UBLogUtil.logE("no instance of settingPlugin");
		}
		return isSupport;
	}

	@Override
	public String callFunction(int functionName) {
		UBLogUtil.logI(TAG+" callFunction");
		String result="";
		if (mUBSettingPlugin!=null) {
			result=mUBSettingPlugin.callFunction(functionName);
		}else{
			UBLogUtil.logE("no instance of settingPlugin");
		}
		return result;
	}

}
