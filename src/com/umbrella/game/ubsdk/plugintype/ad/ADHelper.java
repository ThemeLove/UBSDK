package com.umbrella.game.ubsdk.plugintype.ad;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class ADHelper {
	public static void addBannerView(WindowManager wm,View bannerContainer,int position){
		LayoutParams wmParams = new WindowManager.LayoutParams();
		wmParams.type=WindowManager.LayoutParams.FIRST_SUB_WINDOW;
		wmParams.width=LayoutParams.WRAP_CONTENT;
		wmParams.height=LayoutParams.WRAP_CONTENT;
		wmParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		
		switch (position) {
		case BannerPosition.TOP:
			wmParams.gravity=Gravity.TOP;
			break;
		case BannerPosition.BOTTOM:
			wmParams.gravity=Gravity.BOTTOM;
			break;
		default://默认显示在顶部
			wmParams.gravity=Gravity.TOP;
			break;
		}
		
		wm.addView(bannerContainer, wmParams);
	}
}
