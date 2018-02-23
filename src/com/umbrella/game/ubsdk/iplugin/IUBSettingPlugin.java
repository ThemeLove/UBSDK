package com.umbrella.game.ubsdk.iplugin;

public interface IUBSettingPlugin {
    
//    public void setDebug(boolean debug);
    
//    public void setIsLandScape(boolean isLandScape);
    
//    public void setShowExitDialog(boolean showExitDialog);
    
    public void exit();
    
    public int getPlatformId();
    
    public int getSubPlatformId();
    
    public String getExtrasConfig(String extras);
    
    public boolean isFunctionSupported(int functionName);
    
    public String callFunction(int functionName);
    
}
