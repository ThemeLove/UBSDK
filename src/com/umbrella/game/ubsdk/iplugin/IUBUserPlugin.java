package com.umbrella.game.ubsdk.iplugin;

import com.umbrella.game.ubsdk.bean.UBUserInfo;

public interface IUBUserPlugin extends IUBPlugin{
	void login();
	void logout();
	UBUserInfo getUserInfo();
	void setGameDataInfo(Object obj,int dataType);
}
