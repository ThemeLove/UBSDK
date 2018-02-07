package com.umbrella.game.ubsdk.utils;

import com.umbrella.game.ubsdk.config.UBGame;

import android.util.Log;

public class UBLogUtil {
	private static final String TAG="UBSDK";
	
	public static void logV(String msg){
		if (UBGame.debugMode) Log.v(TAG, msg);
	}
	
	public static void logD(String msg){
		if (UBGame.debugMode) Log.d(TAG,msg);
	}
	
	public static void logI(String msg){
		if (UBGame.debugMode) Log.i(TAG, msg);
	}
	
	public static void logW(String msg){
		if (UBGame.debugMode) Log.w(TAG, msg);
	}

	public static void logE(String msg){
		if (UBGame.debugMode) Log.e(TAG, msg);
	}
	
//	===========================================
	
	public static void logV(String tag,String msg){
		if (UBGame.debugMode) Log.v(tag, msg);
	}
	
	public static void logD(String tag,String msg){
		if (UBGame.debugMode) Log.d(tag, msg);
	}
	
	public static void logI(String tag,String msg){
		if (UBGame.debugMode) Log.i(tag, msg);
	}
	
	public static void logW(String tag,String msg){
		if (UBGame.debugMode) Log.w(tag, msg);
	}
	
	public static void logE(String tag,String msg){
		if (UBGame.debugMode) Log.e(tag, msg);
	}
	
}
