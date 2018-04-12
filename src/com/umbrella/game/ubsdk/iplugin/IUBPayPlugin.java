package com.umbrella.game.ubsdk.iplugin;

import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;

public interface IUBPayPlugin extends IUBPlugin{
	void pay(UBRoleInfo ubRoleInfo,UBOrderInfo ubOrderInfo);
}
