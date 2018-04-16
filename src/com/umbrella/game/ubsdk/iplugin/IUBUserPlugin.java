package com.umbrella.game.ubsdk.iplugin;

import com.umbrella.game.ubsdk.plugintype.user.UBUserInfo;

public interface IUBUserPlugin extends IUBPlugin{
	public static final int PLUGIN_TYPE=PluginType.PLUGIN_TYPE_USER;
	void login();
	void logout();
	UBUserInfo getUserInfo();
	void setGameDataInfo(Object obj,int dataType);
}
