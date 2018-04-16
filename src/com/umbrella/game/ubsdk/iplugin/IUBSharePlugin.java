package com.umbrella.game.ubsdk.iplugin;

import com.umbrella.game.ubsdk.plugintype.share.UBShareParams;

public interface IUBSharePlugin extends IUBPlugin{
	public static final int PLUGIN_TYPE=PluginType.PLUGIN_TYPE_SHARE;
	/***
	 * 分享
	 * @param params
	 */
	public void share(UBShareParams params);
}
