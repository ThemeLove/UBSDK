package com.umbrella.game.ubsdk.factory;

import java.lang.reflect.Constructor;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.demo.plugin.DemoInitPlugin;
import com.umbrella.game.ubsdk.demo.plugin.DemoPayPlugin;
import com.umbrella.game.ubsdk.demo.plugin.DemoSettingPlugin;
import com.umbrella.game.ubsdk.demo.plugin.DemoUserPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;
import android.text.TextUtils;

public class PluginFactory {
	private static final String TAG=PluginFactory.class.getSimpleName();
    /**
     * 实例化插件
     */
    public static Object newPlugin(int pluginType) {
    	int initTypeValue= PluginType.PLUGIN_TYPE_INIT.getPluginType();
    	int userTypeValue= PluginType.PLUGIN_TYPE_USER.getPluginType();
    	int payTypeValue= PluginType.PLUGIN_TYPE_PAY.getPluginType();
    	int settingTypeValue=PluginType.PLUGIN_TYPE_SETTING.getPluginType();
    	
    	String demoInitPluginName=DemoInitPlugin.class.getName();
    	String demoUserPluginName=DemoUserPlugin.class.getName();
    	String demoPayPluginName=DemoPayPlugin.class.getName();
    	String demoSettingPluginName=DemoSettingPlugin.class.getName();
    	
        String pluginName=UBSDKConfig.getInstance().getPluginMap().get(pluginType);
        
//      如果配置文件中读到的插件名为空，则默认实例化对应的demo插件
        if (TextUtils.isEmpty(pluginName)) {
        	if (initTypeValue==pluginType) {
				pluginName=demoInitPluginName;
			}else if(userTypeValue==pluginType){
				pluginName=demoUserPluginName;
			}else if (payTypeValue==pluginType){
				pluginName=demoPayPluginName;
			}else if(settingTypeValue==pluginType){
				pluginName=demoSettingPluginName;
			}
        }
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
