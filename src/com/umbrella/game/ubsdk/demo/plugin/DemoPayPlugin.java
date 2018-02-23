package com.umbrella.game.ubsdk.demo.plugin;

import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;
import com.umbrella.game.ubsdk.iplugin.IUBPayPlugin;

import android.app.Activity;

public class DemoPayPlugin implements IUBPayPlugin{
	private Activity mActivity;
	private DemoPayPlugin(Activity activity){
		this.mActivity=activity;
	}

	@Override
	public void pay(UBRoleInfo ubRoleInfo, UBOrderInfo ubOrderInfo) {
		DemoSDK.getInstance().pay(ubRoleInfo, ubOrderInfo);
	}

}
