package com.umbrella.game.ubsdk.demo.plugin;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.iplugin.IUBSettingPlugin;
import com.umbrella.game.ubsdk.utils.TextUtil;

import android.app.Activity;

public class DemoSettingPlugin implements IUBSettingPlugin{
	private Activity mActivity;
	private DemoSettingPlugin(Activity activity){
		this.mActivity=activity;
	}
	
	
	@Override
	public void exit() {
		DemoSDK.getInstance().exit();
	}
	

	@Override
	public int getPlatformId() {
		int platform=-1;
		try {
			String platformIdStr = UBSDKConfig.getInstance().getParamsMap().get("UBSDK_PlatformId");
			platform=Integer.valueOf(platformIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return platform;
		}
		
		return platform;
	}

	@Override
	public int getSubPlatformId() {
		int subPlatform=-1;
		try {
			String subPlatformIdStr = UBSDKConfig.getInstance().getParamsMap().get("UBSDK_SubPlatformId");
			subPlatform=Integer.valueOf(subPlatformIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return subPlatform;
		}
		return subPlatform;
	}

	@Override
	public String getExtrasConfig(String extras) {
		
		return null;
	}

	@Override
	public boolean isFunctionSupported(int functionName) {
		return false;
	}

	@Override
	public String callFunction(int functionName) {
		return null;
	}


	@Override
	public void gamePause() {
		DemoSDK.getInstance().gamePause();
	}
}
