package com.umbrella.game.ubsdk.iplugin;

import com.umbrella.game.ubsdk.bean.DataType;
import com.umbrella.game.ubsdk.bean.UBUserInfo;

public interface IUBUserPlugin {
	PluginType pluginType=PluginType.PLUGIN_TYPE_USER;
	void login();
	void logout();
	UBUserInfo getUserInfo();
	void setGameDataInfo(Object obj,DataType dataType);
}
