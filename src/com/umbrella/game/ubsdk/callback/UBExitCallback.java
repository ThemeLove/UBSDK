package com.umbrella.game.ubsdk.callback;

public interface UBExitCallback {
	/**
	 * 退出成功，渠道有退出框功能，用户点击了"退出"时的callback
	 */
    public void onExit();
    
    /**
     * 退出失败，渠道有退出框功能，用户点击了"取消"时的callback
     * @param message
     * @param trace
     */
    public void onCancel(String message, String trace);
    
    /**
     * 渠道没有退出框功能，需要游戏自己处理退出框功能
     */
    public void noImplement();
}
