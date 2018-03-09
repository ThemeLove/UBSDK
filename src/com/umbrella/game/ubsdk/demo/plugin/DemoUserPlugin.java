package com.umbrella.game.ubsdk.demo.plugin;

import com.umbrella.game.ubsdk.bean.UBUserInfo;
import com.umbrella.game.ubsdk.iplugin.IUBUserPlugin;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;

public class DemoUserPlugin implements IUBUserPlugin{
	private final String TAG=DemoUserPlugin.class.getSimpleName();
	private Activity mActivity;
	private DemoUserPlugin(Activity activity){
		this.mActivity=activity;
	}

	@Override
	public void login() {
		UBLogUtil.logI(TAG+"----->login");
		DemoSDK.getInstance().login();
	}

	@Override
	public void logout() {
		UBLogUtil.logI(TAG+"----->logout");
		DemoSDK.getInstance().logout();
	}

	@Override
	public UBUserInfo getUserInfo() {
		UBLogUtil.logI(TAG+"----->getUserInfo");
		return null;
	}

	@Override
	public void setGameDataInfo(Object obj, int dataType) {
		UBLogUtil.logI(TAG+"----->setGameDataInfo");
		DemoSDK.getInstance().setGameDataInfo(obj, dataType);
	}

}
