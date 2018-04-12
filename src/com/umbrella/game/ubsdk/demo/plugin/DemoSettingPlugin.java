package com.umbrella.game.ubsdk.demo.plugin;

import java.lang.reflect.Method;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.iplugin.IUBSettingPlugin;
import com.umbrella.game.ubsdk.utils.TextUtil;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;

public class DemoSettingPlugin implements IUBSettingPlugin{
	private final String TAG=DemoSettingPlugin.class.getSimpleName();
	private Activity mActivity;
	private DemoSettingPlugin(Activity activity){
		this.mActivity=activity;
	}
	
	@Override
	public void exit() {
		UBLogUtil.logI(TAG+"----->exit");
		DemoSDK.getInstance().exit();
	}
	

	@Override
	public int getPlatformID() {
		UBLogUtil.logI(TAG+"----->getPlatformID");
		
		int platform=-1;
		try {
			String platformIDStr = UBSDKConfig.getInstance().getParamsMap().get(UBSDKConfig.UB_PlatformID);
			if (!TextUtil.isEmpty(platformIDStr)) {
				platform=Integer.valueOf(platformIDStr);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return platform;
		}
		return platform;
	}
	
	@Override
	public String getPlatformName() {
		UBLogUtil.logI(TAG+"----->getPlatformName");
		
		String platformName="demo";
		try {
			platformName = UBSDKConfig.getInstance().getParamsMap().get(UBSDKConfig.UB_PlatformName);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return platformName;
		}
		return platformName;
	}

	@Override
	public int getSubPlatformID() {
		UBLogUtil.logI(TAG+"----->getSubPlatformID");
		
		int subPlatform=-1;
		try {
			String subPlatformIDStr = UBSDKConfig.getInstance().getParamsMap().get(UBSDKConfig.UB_SubPlatformID);
			if (!TextUtil.isEmpty(subPlatformIDStr)) {
				subPlatform=Integer.valueOf(subPlatformIDStr);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return subPlatform;
		}
		return subPlatform;
	}

	@Override
	public String getExtrasConfig(String extras) {
		UBLogUtil.logI(TAG+"----->getExtrasConfig");
		return null;
	}

	@Override
	public boolean isFunctionSupported(int functionName) {
		UBLogUtil.logI(TAG+"----->isFunctionSupported");
		return false;
	}

	@Override
	public String callFunction(int functionName) {
		UBLogUtil.logI(TAG+"----->callFunction");
		return null;
	}


	@Override
	public void gamePause() {
		UBLogUtil.logI(TAG+"----->gamePause");
		DemoSDK.getInstance().gamePause();
	}
	
	@Override
	public boolean isSupportMethod(String methodName) {
        UBLogUtil.logI(TAG+"----->isSupportMethod");
        Method[] methods = DemoSettingPlugin.class.getMethods();
        for(int i = 0; i<methods.length; i++)
        {
            if(methods[i].getName().equals(methodName))
            {
                return true;
            }
        }
		return false;
	}
}
