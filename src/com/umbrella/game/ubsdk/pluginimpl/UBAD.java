package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
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
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->init");
				mUBADPlugin=(IUBADPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_AD);
				if (mUBADPlugin==null) {
					UBLogUtil.logE(TAG+"----->no instance of ADPlugin ,instance DemoADPlugin instead");
					mUBADPlugin=new DemoADPlugin(UBSDKConfig.getInstance().getGameActivity());
				}else{
					UBLogUtil.logI(TAG+"----->create ADPlugin success");
				}
				
			}
		});
		

	}
	
	
	
	private UBADCallback mUBADCallback;

	/**
	 * set the UBAD Callback
	 * @param ubADCallback
	 */
	private void setUBADCallback(final UBADCallback ubADCallback){
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				UBLogUtil.logI(TAG+"----->setUBADCallback");
				mUBADCallback=ubADCallback;
			}
		});

	}
	
	public void showADWithADType(final int adType,final UBADCallback ubADCallback){
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {

				UBLogUtil.logI(TAG+"----->showADWithADtype with adType and UBADcallback");
				setUBADCallback(ubADCallback);
				showADWithADType(adType);
			}
		});
		
	}

	@Override
	public void showADWithADType(final int adType) {
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->showADWithADType");
				if (mUBADPlugin!=null) {
					mUBADPlugin.showADWithADType(adType);
				}else{
					
					UBLogUtil.logE(TAG+"----->no instance of AD Plugin");
					UBADCallback ubADCallback = UBAD.getInstance().getUBADCallback();
					if (ubADCallback!=null) {
						ubADCallback.onFailed(adType, "no instance of AD plugin");
					}
				}
			}
		});
		
	}

	@Override
	public void hideADWithADType(final int adType) {
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->hideADWithADType");
				if (mUBADPlugin!=null) {
					mUBADPlugin.hideADWithADType(adType);
				}else{
					UBLogUtil.logE(TAG+"----->no instance of AD Plugin");
					UBADCallback ubADCallback = UBAD.getInstance().getUBADCallback();
					if (ubADCallback!=null) {
						ubADCallback.onFailed(adType, "no instance of AD plugin");
					}
				}
			}
			
		});
	
	}

	/*************************************************** 有返回值的方法 ***************************************************/
	/**
	 * return the UBAD Callback
	 * @return
	 */
	public UBADCallback getUBADCallback(){
		UBLogUtil.logI(TAG+"----->getUBADCallback");
		return mUBADCallback;
	}
	
	@Override
	public boolean isSupportADType(int adType) {
		UBLogUtil.logI(TAG+"----->isSupportADType");
		if (mUBADPlugin!=null) {
			return mUBADPlugin.isSupportADType(adType);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of AD plugin");
			return false;
		}
	}
	
	@Override
	public boolean isSupportMethod(String methodName,Object[] args) {
		UBLogUtil.logI(TAG+"----->isSupportMethod");
		if (mUBADPlugin!=null) {
			return mUBADPlugin.isSupportMethod(methodName,args);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of AD plugin");
			return false;
		}
	}
	
	@Override
	public Object callMethod(String methodName, Object[] args) {
		UBLogUtil.logI(TAG+"----->callMethod");
		if (mUBADPlugin!=null) {
			return mUBADPlugin.callMethod(methodName, args);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of AD plugin");
			return null;
		}
	}
}
