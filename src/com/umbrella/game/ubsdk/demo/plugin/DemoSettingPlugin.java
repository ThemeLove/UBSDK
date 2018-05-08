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
	public DemoSettingPlugin(Activity activity){
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
			String platformIDStr = UBSDKConfig.getInstance().getParamMap().get(UBSDKConfig.UB_PlatformID);
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
			platformName = UBSDKConfig.getInstance().getParamMap().get(UBSDKConfig.UB_PlatformName);
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
			String subPlatformIDStr = UBSDKConfig.getInstance().getParamMap().get(UBSDKConfig.UB_SubPlatformID);
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
	public void gamePause() {
		UBLogUtil.logI(TAG+"----->gamePause");
		DemoSDK.getInstance().gamePause();
	}
	
	@Override
	public boolean isSupportMethod(String methodName,Object[] args) {
        UBLogUtil.logI(TAG+"----->isSupportMethod");
        Class<?> [] parameterTypes=null;
        if (args!=null&&args.length>0) {
        	parameterTypes=new Class<?>[args.length];
			for(int i=0;i<args.length;i++){
				parameterTypes[i]=args[i].getClass();
			}
		}
        
        try {
			Method method = getClass().getDeclaredMethod(methodName, parameterTypes);
			return method==null?false:true;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Object callMethod(String methodName, Object[] args) {
		UBLogUtil.logI(TAG+"----->callMethod");
		Class<?>[] parameterTypes=null;
		if (args!=null&&args.length>0) {
			parameterTypes=new Class<?>[args.length];
			for (int i=0;i<args.length;i++) {
				parameterTypes[i]=args[i].getClass();
			}
		}
		
		try {
			Method method = getClass().getDeclaredMethod(methodName, parameterTypes);
			return method.invoke(this, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
