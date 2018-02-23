package com.umbrella.game.ubsdk.callback;

import com.umbrella.game.ubsdk.bean.UBUserInfo;

public interface UBSwitchAccountCallback {
	/**
	 * 切换账号成功
	 * @param ubUserInfo
	 */
    public void onSuccess(UBUserInfo ubUserInfo);
    /**
     * 切换账号取消
     */
    public void onCancel();
    
    /**
     * 切换账号失败
     * @param message
     * @param trace
     */
    public void onFailed(String message, String trace);
}
