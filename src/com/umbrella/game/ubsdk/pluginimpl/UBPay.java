package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;
import com.umbrella.game.ubsdk.factory.PluginFactory;
import com.umbrella.game.ubsdk.iplugin.IUBPayPlugin;
import com.umbrella.game.ubsdk.iplugin.PluginType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

public class UBPay implements IUBPayPlugin{
	private final String TAG=getClass().getSimpleName();
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
		UBLogUtil.logI(TAG+" init");
		mUBPayPlugin=(IUBPayPlugin) PluginFactory.newPlugin(PluginType.PLUGIN_TYPE_PAY.getPluginType());
		if (mUBPayPlugin==null) {
			UBLogUtil.logE("no instance for payPlugin");
		}else{
			UBLogUtil.logI("create payPlugin success");
		}
	}
	
	@Override
	public void pay(final UBRoleInfo ubRoleInfo, final UBOrderInfo ubOrderInfo) {
		UBLogUtil.logI(TAG+" pay");
		if (mUBPayPlugin!=null) {
			UBSDK.getInstance().runOnUIThread(new Runnable() {
				
				@Override
				public void run() {
					mUBPayPlugin.pay(ubRoleInfo, ubOrderInfo);
				}
			});
		}else{
			UBLogUtil.logE("no instance for payPlugin");
		}
	}

}
