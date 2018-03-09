package com.umbrella.game.ubsdk.factory;

import com.umbrella.game.ubsdk.listener.IChannelProxyApplication;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.text.TextUtils;

/**
 * 实例化渠道代理Application ChannelProxyApplication
 * @author qingshanliao
 */
public class ApplicationFactory {
	private static final String TAG=ApplicationFactory.class.getSimpleName();
	public static IChannelProxyApplication getChannelProxyApplication(String applicationName){
		
		UBLogUtil.logI(TAG+"----->getChannelProxyApplication:application----->"+applicationName);
		
		if (TextUtils.isEmpty(applicationName)) return null;
		try {
			Class<?> clazz = Class.forName(applicationName);
			return (IChannelProxyApplication) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			UBLogUtil.logE(TAG+"----->fail to init application----->"+applicationName);
		}
		return null;
	}
}
