package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;
import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.demo.plugin.DemoPayPlugin;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBPayPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class UBPay implements IUBPayPlugin{
	private final String TAG=UBPay.class.getSimpleName();
	private IUBPayPlugin mUBPayPlugin;
	private static UBPay instance;
	private UBPay(){}
	public static UBPay getInstance(){
		if (instance==null) {
			synchronized (UBPay.class) {
				if (instance == null) {
					instance = new UBPay();
				}
			}
		}
		return instance;
	}

	public void init(){
		UBLogUtil.logI(TAG+"----->init");
		mUBPayPlugin=(IUBPayPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_PAY);
		if (mUBPayPlugin==null) {
			UBLogUtil.logE(TAG+"----->no instance of payPlugin");
			mUBPayPlugin=new DemoPayPlugin(UBSDKConfig.getInstance().getGameActivity());
		}else{
			UBLogUtil.logI(TAG+"----->create payPlugin success");
		}
	}
	
	@Override
	public void pay(final UBRoleInfo ubRoleInfo, final UBOrderInfo ubOrderInfo) {
		UBLogUtil.logI(TAG+"----->pay");
		if (mUBPayPlugin!=null) {
			UBSDK.getInstance().runOnUIThread(new Runnable() {
				
				@Override
				public void run() {
					mUBPayPlugin.pay(ubRoleInfo, ubOrderInfo);
				}
			});
		}else{
			UBSDK.getInstance().runOnUIThread(new Runnable() {
				
				@Override
				public void run() {
					UBLogUtil.logE(TAG+"----->no instance of payPlugin");
					UBSDK.getInstance().getUBPayCallback().onFailed("","no instance of payPlugin",null); 
				}
			});
			
		}
	}
	@Override
	public boolean isSupportMethod(String methodName,Object[] args) {
		UBLogUtil.logI(TAG+"----->isSupportMethod");
		if (mUBPayPlugin!=null) {
			return mUBPayPlugin.isSupportMethod(methodName,args);
		}
		return false;
	}
	@Override
	public Object callMethod(String methodName, Object[] args) {
		UBLogUtil.logI(TAG+"----->callMethod");
		if (mUBPayPlugin!=null) {
			return mUBPayPlugin.callMethod(methodName, args);
		}
		return null;
	}

}
