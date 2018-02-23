package com.umbrella.game.ubsdk.iplugin;

public enum PluginType {
	PLUGIN_TYPE_USER(1,"user"),
	PLUGIN_TYPE_PAY(2,"pay"),
	PLUGIN_TYPE_SETTING(3,"setting");
	
	private int mPluginType;
	private String mPluginName;
	private PluginType(int pluginType,String pluginName){
		this.mPluginType=pluginType;
		this.mPluginName=pluginName;
	}
	
	public String getPluginName(){
		return mPluginName;
	}
	
	public int getPluginType(){
		return mPluginType;
	}
}
