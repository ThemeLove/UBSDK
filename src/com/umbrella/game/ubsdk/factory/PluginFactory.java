package com.umbrella.game.ubsdk.factory;

import java.lang.reflect.Constructor;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;

public class PluginFactory {
	private static final String TAG=PluginFactory.class.getSimpleName();
    /**
     * 实例化插件
     */
    public static Object newPlugin(int pluginType) {
        String pluginName=UBSDKConfig.getInstance().getPluginMap().get(pluginType);
		UBLogUtil.logI(TAG+"----->pluginName ----->"+pluginName);
        try {
            Class<?> clazz = Class.forName(pluginName);
            Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[] {
                Activity.class
            });
            constructor.setAccessible(true);
            return constructor.newInstance(new Object[] {
                UBSDKConfig.getInstance().getGameActivity()
            });
        } catch (Exception e) {
        	e.printStackTrace();
        	try {
        		UBLogUtil.logE(TAG+"----->fail to init plugin ----->"+pluginName);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
        return null;
    }
}
