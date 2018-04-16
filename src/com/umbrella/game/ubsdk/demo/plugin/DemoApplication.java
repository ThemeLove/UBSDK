package com.umbrella.game.ubsdk.demo.plugin;

import com.umbrella.game.ubsdk.listener.IChannelProxyApplication;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class DemoApplication implements IChannelProxyApplication{
	private final String TAG=getClass().getSimpleName();

	@Override
	public void onProxyCreate(Application application) {
		UBLogUtil.logI(TAG+"----->onProxyCreate");
	}

	@Override
	public void onProxyAttachBaseContext(Application application, Context base) {
		UBLogUtil.logI(TAG+"----->onProxyAttachBaseContext");
	}

	@Override
	public void onProxyConfigurationChanged(Application application, Configuration config) {
		UBLogUtil.logI(TAG+"----->onProxyConfigurationChanged");
	}

	@Override
	public void onTerminate() {
		UBLogUtil.logI(TAG+"----->onTerminate");
	}
}
