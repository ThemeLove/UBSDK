package com.umbrella.game.ubsdk.pluginimpl;

import com.umbrella.game.ubsdk.bean.UBUserInfo;
import com.umbrella.game.ubsdk.iplugin.IUBUserPlugin;

public class UBUser implements IUBUserPlugin{
	
	private IUBUserPlugin mUBUserPlugin;
	
	private static UBUser instance=null;
	
	private UBUser(){}
	
	public static UBUser getInstance(){
		if (instance==null) {
			synchronized (UBUser.class) {
				if (instance == null) {
					instance = new UBUser();
				}
			}
		}
		return instance;
	}
	
	public void init(){
//		mUBUserPlugin=
	}

	@Override
	public void login() {
		
	}

	@Override
	public void logout() {
		
	}

	@Override
	public UBUserInfo getUserInfo() {
		return null;
	}

}
