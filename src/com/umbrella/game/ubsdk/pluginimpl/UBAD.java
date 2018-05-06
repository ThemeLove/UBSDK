package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.callback.UBADCallback;
import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.demo.plugin.DemoADPlugin;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBADPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class UBAD implements IUBADPlugin{
	private static final String TAG=UBAD.class.getSimpleName();
	private IUBADPlugin mUBADPlugin=null;
	private static UBAD instance=null;
	private UBAD(){ }
	
	public static UBAD getInstance(){
		if (instance==null) {
			synchronized (UBAD.class) {
				if (instance == null) {
					instance = new UBAD();
				}
			}
		}
		return instance;
	}

	public void init(){
		UBLogUtil.logI(TAG+"----->init");
		mUBADPlugin=(IUBADPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_AD);
		if (mUBADPlugin==null) {
			UBLogUtil.logE(TAG+"----->no instance of ADPlugin ,instance DemoADPlugin instead");
			mUBADPlugin=new DemoADPlugin(UBSDKConfig.getInstance().getGameActivity());
		}else{
			UBLogUtil.logI(TAG+"----->create ADPlugin success");
		}
	}
	
	private UBADCallback mUBADCallback;
	
	/**
	 * set the UBAD Callback
	 * @param ubADCallback
	 */
	public void setUBADCallback(UBADCallback ubADCallback){
		UBLogUtil.logI(TAG+"----->setUBADCallback");
		mUBADCallback=ubADCallback;
	}
	
	/**
	 * return the UBAD Callback
	 * @return
	 */
	public UBADCallback getUBADCallback(){
		UBLogUtil.logI(TAG+"----->getUBADCallback");
		return mUBADCallback;
	}

	@Override
	public boolean isSupportMethod(String methodName,Object[] args) {
		UBLogUtil.logI(TAG+"----->isSupportMethod");
		if (mUBADPlugin!=null) {
			return mUBADPlugin.isSupportMethod(methodName,args);
		}
		return false;
	}
	@Override
	public Object callMethod(String methodName, Object[] args) {
		UBLogUtil.logI(TAG+"----->callMethod");
		if (mUBADPlugin!=null) {
			return mUBADPlugin.callMethod(methodName, args);
		}
		return null;
	}

	@Override
	public boolean isSupportADType(int adType) {
		UBLogUtil.logI(TAG+"----->isSupportADType");
		if (mUBADPlugin!=null) {
			return mUBADPlugin.isSupportADType(adType);
		}
		return false;
	}

	@Override
	public void showADWithADType(int adType) {
		UBLogUtil.logI(TAG+"----->showADWithADType");
		if (mUBADPlugin!=null) {
			mUBADPlugin.showADWithADType(adType);
		}
	}

	@Override
	public void hideADWithADType(int adType) {
		UBLogUtil.logI(TAG+"----->hideADWithADType");
		if (mUBADPlugin!=null) {
			mUBADPlugin.hideADWithADType(adType);
		}
	}

}
