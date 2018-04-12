package com.umbrella.game.ubsdk.iplugin;

public interface IUBPlugin {
	/**
	 * 插件是否支持某个方法
	 * @param methodName 方法名
	 * @return true 支持 ;false 不支持
	 */
	boolean isSupportMethod(String methodName);
}
