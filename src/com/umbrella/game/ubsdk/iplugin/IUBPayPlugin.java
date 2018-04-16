package com.umbrella.game.ubsdk.iplugin;

import com.umbrella.game.ubsdk.plugintype.pay.UBOrderInfo;
import com.umbrella.game.ubsdk.plugintype.user.UBRoleInfo;

public interface IUBPayPlugin extends IUBPlugin{
	public static final int PLUGIN_TYPE=PluginType.PLUGIN_TYPE_PAY;
	void pay(UBRoleInfo ubRoleInfo,UBOrderInfo ubOrderInfo);
}
