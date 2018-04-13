package com.umbrella.game.ubsdk.iplugin;

public interface IUBPlugin {
	/**
	 * 插件是否支持某个方法
	 * @param methodName	方法名
	 * @param args			参数		没有传null
	 * @return				true 支持;	false 不支持
	 */
	boolean isSupportMethod(String methodName,Object[] args);
	
	/**
	 * 调用插件中指定方法名和指定参数的方法
	 * @param methodName	方法名
	 * @param args			参数     没有传null
	 * @return				返回值
	 */
	Object callMethod(String methodName,Object[] args);
}
