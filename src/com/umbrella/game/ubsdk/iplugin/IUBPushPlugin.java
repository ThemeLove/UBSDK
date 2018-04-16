package com.umbrella.game.ubsdk.iplugin;

public interface IUBPushPlugin extends IUBPlugin{
	public static final int PLUGIN_TYPE=PluginType.PLUGIN_TYPE_PUSH;

	/**
	 * 执行通知
	 * @param msgs
	 */
	public void scheduleNotification(String msgs);
	
	/**
	 * 开始推送
	 */
	public void startPush();
	
	/**
	 * 停止推送
	 */
	public void stopPush();
	
	/**
	 * 添加tag
	 * @param tags
	 */
	public void addTags(String...tags);
	
	/**
	 * 删除tag
	 * @param tag
	 */
	public void removeTags(String...tag);
	
	/**
	 * 添加别名
	 * @param alias
	 */
	public void addAlias(String alias);
	
	/**
	 * 删除别名
	 * @param alias
	 */
	public void removeAlias(String alias);
}
