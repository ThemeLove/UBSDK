package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.demo.plugin.DemoPayPlugin;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBPayPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.plugintype.pay.UBOrderInfo;
import com.umbrella.game.ubsdk.plugintype.user.UBRoleInfo;
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
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				UBLogUtil.logI(TAG+"----->init");
				mUBPayPlugin=(IUBPayPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_PAY);
				if (mUBPayPlugin==null) {
					UBLogUtil.logE(TAG+"----->no instance of payPlugin ,instance DemoPayPlugin instead");
					mUBPayPlugin=new DemoPayPlugin(UBSDKConfig.getInstance().getGameActivity());
				}else{
					UBLogUtil.logI(TAG+"----->create payPlugin success");
				}
			}
		});
		
	}
	
	@Override
	public void pay(final UBRoleInfo ubRoleInfo, final UBOrderInfo ubOrderInfo) {
		
		UBSDK.getInstance().runOnUIThread(new Runnable() {
			@Override
			public void run() {
				
				UBLogUtil.logI(TAG+"----->pay");
				if (mUBPayPlugin!=null) {
					mUBPayPlugin.pay(ubRoleInfo, ubOrderInfo);
				}else{
					UBLogUtil.logE(TAG+"----->no instance of payPlugin");
					UBSDK.getInstance().getUBPayCallback().onFailed("","no instance of payPlugin",null); 
				}
				
			}
		});
		
	}
	
	/*************************************************** 有返回值的方法 ***************************************************/
	@Override
	public boolean isSupportMethod(String methodName,Object[] args) {
		UBLogUtil.logI(TAG+"----->isSupportMethod");
		if (mUBPayPlugin!=null) {
			return mUBPayPlugin.isSupportMethod(methodName,args);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of payPlugin");
			return false;
		}
	}
	
	@Override
	public Object callMethod(String methodName, Object[] args) {
		UBLogUtil.logI(TAG+"----->callMethod");
		if (mUBPayPlugin!=null) {
			return mUBPayPlugin.callMethod(methodName, args);
		}else{
			UBLogUtil.logE(TAG+"----->no instance of payPlugin");
			return null;
		}
	}

}
