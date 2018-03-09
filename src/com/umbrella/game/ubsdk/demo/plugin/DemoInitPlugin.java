package com.umbrella.game.ubsdk.demo.plugin;

import com.umbrella.game.ubsdk.iplugin.IUBInitPlugin;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;

public class DemoInitPlugin implements IUBInitPlugin{
	private final String TAG=DemoInitPlugin.class.getSimpleName();
	private Activity mActivity;
	private DemoInitPlugin(Activity activity){
		this.mActivity=activity;
	};
	
	@Override
	public void initChannel() {
		UBLogUtil.logI(TAG+"----->initChannel");
		DemoSDK.getInstance().init();
	}
}
