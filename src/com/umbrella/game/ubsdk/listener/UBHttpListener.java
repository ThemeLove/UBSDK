package com.umbrella.game.ubsdk.listener;

/**
 * 网络接口监听
 * @author qingshanliao
 * @param <T>
 */
public interface UBHttpListener <T>{
	/**
	 * 网络请求成功
	 * @param t
	 */
	void onSuccess(T t);
	/**
	 * 网络请求失败
	 * @param errorMsg errorMsg
	 */
	void onFailed(String errorMsg);
}
