package com.umbrella.game.ubsdk.demo.plugin;

import java.lang.reflect.Method;

import com.umbrella.game.ubsdk.bean.UBOrderInfo;
import com.umbrella.game.ubsdk.bean.UBRoleInfo;
import com.umbrella.game.ubsdk.iplugin.IUBPayPlugin;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;

public class DemoPayPlugin implements IUBPayPlugin{
	private final String TAG=DemoPayPlugin.class.getSimpleName();
	private Activity mActivity;
	public DemoPayPlugin(Activity activity){
		this.mActivity=activity;
	}

	@Override
	public void pay(UBRoleInfo ubRoleInfo, UBOrderInfo ubOrderInfo) {
		UBLogUtil.logI(TAG+"----->pay");
		DemoSDK.getInstance().pay(ubRoleInfo, ubOrderInfo);
	}

	@Override
	public boolean isSupportMethod(String methodName) {
        UBLogUtil.logI(TAG+"----->isSupportMethod");
        Method[] methods = DemoPayPlugin.class.getMethods();
        for(int i = 0; i<methods.length; i++)
        {
            if(methods[i].getName().equals(methodName))
            {
                return true;
            }
        }
		return false;
	}

}
