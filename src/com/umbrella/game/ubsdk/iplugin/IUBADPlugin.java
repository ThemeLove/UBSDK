package com.umbrella.game.ubsdk.iplugin;

public interface IUBADPlugin extends IUBPlugin{
	public static final int PLUGIN_TYPE=PluginType.PLUGIN_TYPE_AD;
	
	/**
	 * 是否支持某种广告类型
	 * @param adType
	 * @return
	 */
	boolean isSupportADType(int adType);
	
	/**
	 * 根据广告类型显示广告
	 * @param adType  广告类型
	 */
	void showADWithADType(int adType);
	
	/**
	 * 根据广告类型隐藏广告
	 * @param adType 广告类型
	 */
	void hideADWithADType(int adType);
}
