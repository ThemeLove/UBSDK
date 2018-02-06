package com.umbrella.game.ubsdk.callback;

public interface UBLogoutCallback {
	/**
	 * 注销成功
	 */
    public void onSuccess();

    /**
     * 注销失败
     * @param message
     * @param trace
     */
    public void onFailed(String message, String trace);
}
