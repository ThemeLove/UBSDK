package com.umbrella.game.ubsdk.callback;

import com.umbrella.game.ubsdk.plugintype.user.UBUserInfo;

public interface UBLoginCallback {

	/**
	 * 登录成功 login success
	 * @param ubUserInfo UBUserInfo
	 */
    public void onSuccess(UBUserInfo ubUserInfo);
    
    /**
     * 登录取消 login cancel
     */
    public void onCancel();
    
    /**
     * 登录失败 login fail
     * @param message
     * @param trace
     */
    public void onFailed(String message, String trace);
}
