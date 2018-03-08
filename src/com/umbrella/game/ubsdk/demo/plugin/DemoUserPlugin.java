package com.umbrella.game.ubsdk.demo.plugin;

import com.umbrella.game.ubsdk.bean.UBUserInfo;
import com.umbrella.game.ubsdk.iplugin.IUBUserPlugin;

import android.app.Activity;

public class DemoUserPlugin implements IUBUserPlugin{
	private Activity mActivity;
	private DemoUserPlugin(Activity activity){
		this.mActivity=activity;
		DemoSDK.getInstance().init();
	}

	@Override
	public void login() {
		DemoSDK.getInstance().login();
	}

	@Override
	public void logout() {
		DemoSDK.getInstance().logout();
	}

	@Override
	public UBUserInfo getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGameDataInfo(Object obj, int dataType) {
		// TODO Auto-generated method stub
		DemoSDK.getInstance().setGameDataInfo(obj, dataType);
	}

}
