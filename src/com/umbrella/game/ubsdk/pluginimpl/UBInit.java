package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBInitPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class UBInit implements IUBInitPlugin {
	private final String TAG=UBInit.class.getSimpleName();
	private static UBInit instance=null;
	private UBInit(){ }
	public static  UBInit getInstance(){
		if (instance==null) {
			if (instance==null) {
				instance=new UBInit();
			}
		}
		return instance;
	}
	
	private IUBInitPlugin mUBInitPlugin;
	
	public void init() {
		UBLogUtil.logI(TAG+"----->init");
		mUBInitPlugin=(IUBInitPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_INIT.getPluginType());
		
		if (mUBInitPlugin!=null) {
			UBLogUtil.logI(TAG+"----->create initPlugin success");
		}else{
			UBLogUtil.logE(TAG+"----->no instance of initPlugin");
		}
	}
	
	
	@Override
	public void initChannel() {
		UBLogUtil.logI(TAG+"----->initChannel");
		if (mUBInitPlugin!=null) {
			UBSDK.getInstance().runOnUIThread(new Runnable() {
				@Override
				public void run() {
					mUBInitPlugin.initChannel();
				}
			});
		}else{
			UBSDK.getInstance().runOnUIThread(new Runnable() {
				@Override
				public void run() {
					UBSDK.getInstance().getUBInitCallback().onFailed("no instance of initPlugin", null);
				}
			});
		}
	}
}
