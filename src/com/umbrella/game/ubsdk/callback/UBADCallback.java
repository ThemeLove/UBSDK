package com.umbrella.game.ubsdk.callback;

public interface UBADCallback {
	void onInit(boolean isInitSuccess,String msg);
	void onShow(int adType,String msg);
	void onFailed(int adType,String msg);
	void onClosed(int adType,String msg);
	void onClick(int adType,String msg);
	void onComplete(int adType,String msg);
}
