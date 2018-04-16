package com.umbrella.game.ubsdk.iplugin;

public interface IUBSettingPlugin extends IUBPlugin{
	public static final int PLUGIN_TYPE=PluginType.PLUGIN_TYPE_SETTING;
    
//    public void setDebug(boolean debug);
    
//    public void setIsLandScape(boolean isLandScape);
    
//    public void setShowExitDialog(boolean showExitDialog);
	
	/**
	 * 游戏暂停接口
	 */
	public void gamePause();
    
    public void exit();
    
    public int getPlatformID();
    
    /**
     * 获取激活平台名字的英文缩写 比如baidu、vivo
     * @return
     */
    public String getPlatformName();
    
    public int getSubPlatformID();
    
    public String getExtrasConfig(String extras);
    
}
