package com.umbrella.game.ubsdk.callback;

public interface UBInitCallback {
	
	/**
	 * 初始化成功
	 */
    void onSuccess();
    
    /**
     * 初始化失败
     * @param message
     * @param trace
     */
    void onFailed(String message, String trace);
}
