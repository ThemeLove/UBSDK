package com.umbrella.game.ubsdk.callback;

public interface UBGamePauseCallback {
	/**
	 * 游戏暂停
	 */
	void onGamePause();
	/**
	 * 游戏暂停失败，一般不用关注改回调
	 */
	void onFailed(String msg);
}
