package com.umbrella.game.ubsdk.demo.plugin;

import java.lang.reflect.Method;

import com.umbrella.game.ubsdk.UBSDK;
import com.umbrella.game.ubsdk.iplugin.IUBADPlugin;
import com.umbrella.game.ubsdk.listener.UBActivityListenerImpl;
import com.umbrella.game.ubsdk.plugintype.ad.ADType;
import com.umbrella.game.ubsdk.utils.UBLogUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class DemoADPlugin implements IUBADPlugin{
	private static final String TAG=DemoADPlugin.class.getSimpleName();
	private Activity mActivity;
	
	private int[] supportedADTypeArray=new int[]{ADType.AD_TYPE_BANNER,ADType.AD_TYPE_INTERSTITIAL,ADType.AD_TYPE_SPLASH,ADType.AD_TYPE_REWARDEDVIDEO};
	
	public DemoADPlugin(Activity activity){
		this.mActivity=activity;
//		TODO loadADParams  加载广告参数
//		设置ActivityListener监听
		setActivityListener();
	}

	private void setActivityListener(){
		UBSDK.getInstance().setUBActivityListener(new UBActivityListenerImpl(){
			
			@Override
			public void onCreate(Bundle savedInstanceState) {
				UBLogUtil.logI(TAG+"----->onCreate");
			}

			@Override
			public void onRestart() {
				UBLogUtil.logI(TAG+"----->onRestart");
			}

			@Override
			public void onStart() {
				UBLogUtil.logI(TAG+"----->onStart");
			}

			@Override
			public void onPause() {
				UBLogUtil.logI(TAG+"----->onPause");
			}

			@Override
			public void onResume() {
				UBLogUtil.logI(TAG+"----->onResume");
			}

			@Override
			public void onAttachedToWindow() {
				UBLogUtil.logI(TAG+"----->onAttachedToWindow");
			}

			@Override
			public void onStop() {
				UBLogUtil.logI(TAG+"----->onStop");
			}

			@Override
			public void onDestroy() {
				UBLogUtil.logI(TAG+"----->onDestroy");
			}

			@Override
			public void onNewIntent(Intent newIntent) {
				UBLogUtil.logI(TAG+"----->onNewIntent");
			}

			@Override
			public void onConfigurationChanged(Configuration newConfig) {
				UBLogUtil.logI(TAG+"----->onConfigurationChanged");
			}

			@Override
			public void onActivityResult(int requestCode, int resultCode, Intent data) {
				UBLogUtil.logI(TAG+"----->onActivityResult");
			}

			@Override
			public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
				UBLogUtil.logI(TAG+"----->onRequestPermissionResult");
			}

			@Override
			public void onBackPressed() {
				UBLogUtil.logI(TAG+"----->onBackPressed");
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
		case ADType.AD_TYPE_INTERSTITIAL://插屏广告
			showInterstitialAD();
			break;
		case ADType.AD_TYPE_SPLASH://闪屏广告
			showSplashAD();
			break;
		case ADType.AD_TYPE_REWARDEDVIDEO://激励视频广告
			showVideoAD();
			break;
		default:
			UBLogUtil.logE(TAG+"----->no such type of ad");
			break;
		}
	}
	
	private void showBannerAD(){
		UBLogUtil.logI(TAG+"----->showBannerAD");
//		ToastUtil.showToast(mActivity,"showBannerAD");
	}
	
	private void showInterstitialAD() {
		UBLogUtil.logI(TAG+"----->showInterstitialAD");
//		ToastUtil.showToast(mActivity,"showFullScreenAD");
	}
	
	private void showVideoAD() {
		UBLogUtil.logI(TAG+"----->showVideoAD");
//		ToastUtil.showToast(mActivity, "showVideoAD");
	}
	
	private void showSplashAD() {
		UBLogUtil.logI(TAG+"----->showSplashAD");
//		ToastUtil.showToast(mActivity, "showSplashAD");
	}

	@Override
	public void hideADWithADType(int adType) {
		UBLogUtil.logI(TAG+"----->hideADWithADType");
		switch (adType) {
		case ADType.AD_TYPE_BANNER:
			hideBannerAD();
			break;
		case ADType.AD_TYPE_INTERSTITIAL:
			hideInterstitialAD();
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
//		ToastUtil.showToast(mActivity,"hideBannerAD");
	}
	
	private void hideInterstitialAD() {
		UBLogUtil.logI(TAG+"----->hideInterstitialAD");
//		ToastUtil.showToast(mActivity,"hideFullScreenAD");
	}
	

	private void hideVideoAD() {
		UBLogUtil.logI(TAG+"----->hideVideoAD");
//		ToastUtil.showToast(mActivity, "hideVideoAD");
	}

	private void hideSplashAD() {
		UBLogUtil.logI(TAG+"----->hideSplashAD");
//		ToastUtil.showToast(mActivity,"hideSplashAD");
	}
	
}
