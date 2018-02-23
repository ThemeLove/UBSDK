package com.umbrella.game.ubsdk.factory;

import java.lang.reflect.Constructor;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;
import android.text.TextUtils;

public class PluginFactory {
    /**
     * 实例化插件
     */
    public static Object newPlugin(int pluginType) {
        String pluginName=UBSDKConfig.getInstance().getPluginMap().get(pluginType);
        if (TextUtils.isEmpty(pluginName)) {
            return null;
        }

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
        		UBLogUtil.logE("fail to init plugin :"+PluginType.values()[pluginType-1].name());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
        return null;
    }
}
