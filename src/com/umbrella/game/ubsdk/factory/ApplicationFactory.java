package com.umbrella.game.ubsdk.factory;

import com.umbrella.game.ubsdk.listener.IChannelProxyApplication;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.text.TextUtils;

/**
 * 实例化渠道代理Application ChannelProxyApplication
 * @author qingshanliao
 */
public class ApplicationFactory {
	public static IChannelProxyApplication getChannelProxyApplication(String applicationName){
		if (TextUtils.isEmpty(applicationName)) return null;
		try {
			Class<?> clazz = Class.forName(applicationName);
			return (IChannelProxyApplication) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			UBLogUtil.logE("fail to init application :"+applicationName);
		}
		return null;
	}
}
