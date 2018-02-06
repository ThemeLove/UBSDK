package com.umbrella.game.ubsdk.iplugin;

import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBUserInfo;

public interface IUBPayPlugin {
	void pay(UBUserInfo ubUserInfo,UBOrderInfo ubOrderInfo);
}
