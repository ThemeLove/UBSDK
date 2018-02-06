package com.umbrella.game.ubsdk.iplugin;

public enum PluginType {
	PLUGIN_TYPE_USER(1),
	PLUGIN_TYPE_PAY(2),
	PLUGIN_TYPE_SETTING(3);
	
	private int mPluginType;
	private PluginType(int pluginType){
		this.mPluginType=pluginType;
	}
}
