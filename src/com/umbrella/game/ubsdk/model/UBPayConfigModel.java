package com.umbrella.game.ubsdk.model;

import com.umbrella.game.ubsdk.plugintype.pay.PayConfig;

public class UBPayConfigModel {
	private final static String TAG=UBPayConfigModel.class.getSimpleName();
	
	private UBPayConfigModel(){};
	
	private static UBPayConfigModel instance=null;
	
	public static UBPayConfigModel getInstance(){
		if (instance==null) {
			if (instance==null) {
				instance=new UBPayConfigModel();
			}
		}
		return instance;
	}
	
	/**
	 * 加载商店支付配置
	 * @return
	 */
	public PayConfig loadStorePayConfig(String payConfigPath){
		
		PayConfig payConfig = new PayConfig();
		return payConfig;
	}
	
}
