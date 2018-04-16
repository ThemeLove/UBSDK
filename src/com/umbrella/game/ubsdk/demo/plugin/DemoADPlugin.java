package com.umbrella.game.ubsdk.demo.plugin;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.iplugin.IUBADPlugin;
import com.umbrella.game.ubsdk.listener.UBActivityListenerImpl;
import com.umbrella.game.ubsdk.plugintype.ad.ADType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;

public class DemoADPlugin implements IUBADPlugin{
	private static final String TAG=DemoADPlugin.class.getSimpleName();
	private Activity mActivity;
	
	private int[] supportedADTypeArray=new int[]{ADType.AD_TYPE_BANNER};
	
	public DemoADPlugin(Activity activity){
		this.mActivity=activity;
//		TODO loadADParams  加载广告参数
//		设置ActivityListener监听
		setActivityListener();
	}

	private void setActivityListener(){
		UBSDK.getInstance().setUBActivityListener(new UBActivityListenerImpl(){

			@Override
			public void onDestroy() {
				super.onDestroy();
//				TODO销毁广告资源
				
			}
		});
	}
	@Override
	public boolean isSupportMethod(String methodName,Object[] args) {
        UBLogUtil.logI(TAG+"----->isSupportMethod");
        Class<?> [] parameterTypes=null;
        if (args!=null&&args.length>0) {
        	parameterTypes=new Class<?>[args.length];
			for(int i=0;i<args.length;i++){
				parameterTypes[i]=args[i].getClass();
			}
		}
        
        try {
			Method method = getClass().getDeclaredMethod(methodName, parameterTypes);
			return method==null?false:true;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Object callMethod(String methodName, Object[] args) {
		UBLogUtil.logI(TAG+"----->callMethod");
		Class<?>[] parameterTypes=null;
		if (args!=null&&args.length>0) {
			parameterTypes=new Class<?>[args.length];
			for (int i=0;i<args.length;i++) {
				parameterTypes[i]=args[i].getClass();
			}
		}
		
		try {
			Method method = getClass().getDeclaredMethod(methodName, parameterTypes);
			return method.invoke(this, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isSupportADType(int adType) {
		UBLogUtil.logI(TAG+"----->isSupportADType");
		if (supportedADTypeArray!=null&&supportedADTypeArray.length>0) {
			for (int i : supportedADTypeArray) {
				if (i==adType) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void showADWithADType(int adType) {
		UBLogUtil.logI(TAG+"----->showWithADType");
//		显示某种类型的广告之前先隐藏某种广告类型
		hideADWithADType(adType);
		switch (adType) {
		case ADType.AD_TYPE_BANNER://banner广告
			showBannerView();
			break;
		case ADType.AD_TYPE_FULLSCREEN://插屏广告
//			TODO
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		case ADType.AD_TYPE_REWARDEDVIDEO://激励视频广告
//			TODO
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		case ADType.AD_TYPE_SPLASH://闪屏广告
//			TODO
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		default:
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		}
	}
	
	private void showBannerView(){
		UBLogUtil.logI(TAG+"----->showBannerView");
	}

	@Override
	public void hideADWithADType(int adType) {
		UBLogUtil.logI(TAG+"----->hideADWithADType");
		switch (adType) {
		case ADType.AD_TYPE_BANNER:
			hideBannerView();
			break;
		case ADType.AD_TYPE_FULLSCREEN:
//			TODO
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		case ADType.AD_TYPE_REWARDEDVIDEO:
//			TODO
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		case ADType.AD_TYPE_SPLASH:
//			TODO
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		default:
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		}
		
	}
	
	private void hideBannerView(){
		UBLogUtil.logI(TAG+"----->hideBannerView");
	}
}
