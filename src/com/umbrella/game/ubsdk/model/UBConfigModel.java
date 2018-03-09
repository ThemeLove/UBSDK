package com.umbrella.game.ubsdk.model;

import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.factory.ApplicationFactory;
import com.umbrella.game.ubsdk.listener.IChannelProxyApplication;
import com.umbrella.game.ubsdk.utils.AssetUtil;
import com.umbrella.game.ubsdk.utils.TextUtil;

import android.util.Xml;

/**
 * 获取初始化配置相关的Model
 * @author qingshanliao
 */
public class UBConfigModel {
	private static UBConfigModel instance=null;
	private UBConfigModel(){}
	public static UBConfigModel getInstance(){
		if (instance==null) {
			synchronized (UBConfigModel.class) {
				if (instance == null) {
					instance = new UBConfigModel();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 从Asset目录下初始化UBSDK配置
	 * @param isEncrypt
	 */
	public boolean initUBSDKConfig(boolean isEncrypt){
		boolean isSuccess=false;
		
		String configStr="";
		if (isEncrypt) {
			configStr=AssetUtil.getAssetDESConfigStr(UBSDKConfig.getInstance().getApplicationContext(),UBSDKConfig.UBSDK_CONFIG_FILENAME);
		}else{
			configStr=AssetUtil.getAssetConfigStr(UBSDKConfig.getInstance().getApplicationContext(),UBSDKConfig.UBSDK_CONFIG_FILENAME);
		}
		if (TextUtil.isEmpty(configStr)) isSuccess=false;
		
		XmlPullParser parse = Xml.newPullParser();
		try {
			parse.setInput(new StringReader(configStr));
			int eventType = parse.getEventType();
			while (eventType!=XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					String name = parse.getName();
					if ("param".equalsIgnoreCase(name)) {
						String key = parse.getAttributeValue(null,"name");
						String value = parse.getAttributeValue(null,"value");
						if (UBSDKConfig.UB_GameID.equalsIgnoreCase(key)) {
							UBSDKConfig.getInstance().getUBGame().setUbGameID(value);
						}else if (UBSDKConfig.UB_GameDebug.equalsIgnoreCase(key)) {
							boolean debugMode="true".equalsIgnoreCase(value)?true:false;
							UBSDKConfig.getInstance().getUBGame().setDebugMode(debugMode);
						}else if (UBSDKConfig.UB_GameOrientation.equalsIgnoreCase(key)) {
							UBSDKConfig.getInstance().getUBGame().setOrientation(value);
						}else if (UBSDKConfig.UB_GameMainActivityName.equalsIgnoreCase(key)) {
							UBSDKConfig.getInstance().getUBGame().setMainActivityName(value);
						}
						
						else if(UBSDKConfig.UB_PlatformID.equalsIgnoreCase(key)){
							UBSDKConfig.getInstance().getUBChannel().setUbPlatformID(value);
						}else if(UBSDKConfig.UB_SubPlatformID.equalsIgnoreCase(key)){
							UBSDKConfig.getInstance().getUBChannel().setUbSubPlatformID(value);
						}else if (UBSDKConfig.UB_ChannelID.equalsIgnoreCase(key)) {
							UBSDKConfig.getInstance().getUBChannel().setUbChannelID(value);
						}else if (UBSDKConfig.UB_SubChannelID.equalsIgnoreCase(key)) {
							UBSDKConfig.getInstance().getUBChannel().setSubChannelID(value);
						}else{
							UBSDKConfig.getInstance().getParamsMap().put(key, value);
						}
					}
					
					if ("application".equalsIgnoreCase(name)) {
						String application = parse.getAttributeValue(null,"name");
						UBSDKConfig.getInstance().getApplicationList().add(application);
					}
					
					if ("plugin".equalsIgnoreCase(name)) {
						String pluginName = parse.getAttributeValue(null, "name");
						int pluginType = Integer.parseInt(parse.getAttributeValue(null,"type"));
						UBSDKConfig.getInstance().getPluginMap().put(pluginType, pluginName);
						
						isSuccess=true;//这里是解析到右插件标签标示为解析成功
					}
					break;
				default:
					break;
				}
				eventType=parse.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	/**
	 * 实例化渠道代理Application
	 * @return
	 */
	public IChannelProxyApplication getUBProxyChannelApplication(){
		IChannelProxyApplication application = null;
		ArrayList<String> applicationList = UBSDKConfig.getInstance().getApplicationList(); 
		if (applicationList!=null&&applicationList.size()>0) {
			String applicationName = applicationList.get(0);
			application=ApplicationFactory.getChannelProxyApplication(applicationName);
		}
		return application;
	}
}
