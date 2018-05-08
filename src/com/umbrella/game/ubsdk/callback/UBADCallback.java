package com.umbrella.game.ubsdk.callback;

import com.umbrella.game.ubsdk.plugintype.ad.ADType;

public interface UBADCallback {
	
	/**
	 * UBAD onInit callback
	 * @param isInitSuccess 	Whether the initialization was successful
	 * @param msg				description	
	 */
	void onInit(boolean isInitSuccess,String msg);
	/**
	 * UBAD onClick callback
	 * @param adType	{@link ADType}
	 * @param msg		description
	 */
	void onClick(int adType,String msg);
	/**
	 * UBAD onComplete callback 
	 * @param adType   {@link ADType}
	 * @param msg		description
	 */
	void onComplete(int adType,String msg);
	/**
	 * UBAD onShow callback
	 * @param adType	{@link ADType}}
	 * @param msg		description
	 */
	void onShow(int adType,String msg);
	/**
	 * UBAD onClosed callback
	 * @param adType	{@link ADType}}
	 * @param msg		description
	 */
	void onClosed(int adType,String msg);
	/**
	 * UBAD onFailed callback
	 * @param adType	{@link ADType}
	 * @param msg		description
	 */
	void onFailed(int adType,String msg);
}
