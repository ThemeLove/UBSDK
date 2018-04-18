package com.umbrella.game.ubsdk.demo.plugin;

import java.lang.reflect.Method;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.iplugin.IUBADPlugin;
import com.umbrella.game.ubsdk.listener.UBActivityListenerImpl;
import com.umbrella.game.ubsdk.plugintype.ad.ADType;
import com.umbrella.game.ubsdk.utils.ToastUtil;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;

public class DemoADPlugin implements IUBADPlugin{
	private static final String TAG=DemoADPlugin.class.getSimpleName();
	private Activity mActivity;
	
	private int[] supportedADTypeArray=new int[]{ADType.AD_TYPE_BANNER,ADType.AD_TYPE_FULLSCREEN,ADType.AD_TYPE_REWARDEDVIDEO,ADType.AD_TYPE_SPLASH};
	
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
		UBLogUtil.logI(TAG+"----->showADWithADType");
//		显示某种类型的广告之前先隐藏某种广告类型
		hideADWithADType(adType);
		switch (adType) {
		case ADType.AD_TYPE_BANNER://banner广告
			showBannerAD();
			break;
		case ADType.AD_TYPE_FULLSCREEN://插屏广告
			showFullScreenAD();
			break;
		case ADType.AD_TYPE_REWARDEDVIDEO://激励视频广告
			showVideoAD();
			break;
		case ADType.AD_TYPE_SPLASH://闪屏广告
			showSplashAD();
			break;
		default:
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		}
	}
	
	private void showBannerAD(){
		UBLogUtil.logI(TAG+"----->showBannerAD");
		ToastUtil.showToast(mActivity,"showBannerAD");
	}
	
	private void showFullScreenAD() {
		UBLogUtil.logI(TAG+"----->showFullScreenAD");
		ToastUtil.showToast(mActivity,"showFullScreenAD");
	}
	
	private void showVideoAD() {
		UBLogUtil.logI(TAG+"----->showVideoAD");
		ToastUtil.showToast(mActivity, "showVideoAD");
	}
	
	private void showSplashAD() {
		UBLogUtil.logI(TAG+"----->showSplashAD");
		ToastUtil.showToast(mActivity, "showSplashAD");
	}

	@Override
	public void hideADWithADType(int adType) {
		UBLogUtil.logI(TAG+"----->hideADWithADType");
		switch (adType) {
		case ADType.AD_TYPE_BANNER:
			hideBannerAD();
			break;
		case ADType.AD_TYPE_FULLSCREEN:
			hideFullScreenAD();
			break;
		case ADType.AD_TYPE_REWARDEDVIDEO:
			hideVideoAD();
			break;
		case ADType.AD_TYPE_SPLASH:
			hideSplashAD();
			break;
		default:
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		}
		
	}

	private void hideBannerAD(){
		UBLogUtil.logI(TAG+"----->hideBannerAD");
		ToastUtil.showToast(mActivity,"hideBannerAD");
	}
	
	private void hideFullScreenAD() {
		UBLogUtil.logI(TAG+"----->hideFullScreenAD");
		ToastUtil.showToast(mActivity,"hideFullScreenAD");
	}
	

	private void hideVideoAD() {
		UBLogUtil.logI(TAG+"----->hideVideoAD");
		ToastUtil.showToast(mActivity, "hideVideoAD");
	}

	private void hideSplashAD() {
		UBLogUtil.logI(TAG+"----->hideSplashAD");
		ToastUtil.showToast(mActivity,"hideSplashAD");
	}
	
}
