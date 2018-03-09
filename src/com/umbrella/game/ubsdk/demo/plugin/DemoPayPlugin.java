package com.umbrella.game.ubsdk.demo.plugin;

import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;
import com.umbrella.game.ubsdk.iplugin.IUBPayPlugin;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;

public class DemoPayPlugin implements IUBPayPlugin{
	private final String TAG=DemoPayPlugin.class.getSimpleName();
	private Activity mActivity;
	private DemoPayPlugin(Activity activity){
		this.mActivity=activity;
	}

	@Override
	public void pay(UBRoleInfo ubRoleInfo, UBOrderInfo ubOrderInfo) {
		UBLogUtil.logI(TAG+"----->pay");
		DemoSDK.getInstance().pay(ubRoleInfo, ubOrderInfo);
	}

}
