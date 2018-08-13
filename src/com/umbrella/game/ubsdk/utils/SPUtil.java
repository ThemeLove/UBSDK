package com.umbrella.game.ubsdk.utils;

import java.util.Map;
import java.util.Set;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;

/**
 * getSharedPreferences 工具类
 * @author qingshanliao
 */
public class SPUtil {
//	private static final String TAG=SPUtil.class.getSimpleName();
	/**
	 * sp文件对象的名字
	 */
	public static String PREFERENCES_FILENAME="sp_ubsdk";
	/**
	 * 获得sp
	 */
	public static SharedPreferences getSP(Activity activity){
		SharedPreferences sp = activity.getSharedPreferences(PREFERENCES_FILENAME,Context.MODE_PRIVATE);
		return sp;
	}
	/**************************************************** get ****************************************************/
	public static String getString(Activity activity,String key,String defValue){
		return getSP(activity).getString(key, defValue);
	}
	
	public static int getInt(Activity activity,String key,int defValue){
		return getSP(activity).getInt(key, defValue);
	}
	
	public static boolean getBoolean(Activity activity,String key,boolean defValue){
		return getSP(activity).getBoolean(key, defValue);
	}
	
	public static float getFloat(Activity activity,String key,float defValue){
		return getSP(activity).getFloat(key, defValue);
	}
	
	public static long getLong(Activity activity,String key,Long defValue){
		return getSP(activity).getLong(key, defValue);
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static Set<String> getStringSet(Activity activity,String key,Set<String> defValues){
		return getSP(activity).getStringSet(key, defValues);
	}
	
	public static Map<String,?> getAll(Activity activity,String key){
		return getSP(activity).getAll();
	}
	
	/**************************************************** put ****************************************************/
	
	public static void putString(Activity activity,String key,String value){
		getSP(activity).edit().putString(key, value).commit();
	}
	
	public static void putInt(Activity activity,String key,int value){
		getSP(activity).edit().putInt(key, value).commit();
	}
	
	public static void putBoolean(Activity activity,String key,boolean value){
		getSP(activity).edit().putBoolean(key, value).commit();
	}
	
	public static void putFloat(Activity activity,String key,float value){
		getSP(activity).edit().putFloat(key, value).commit();
	}
	
	public static void putLong(Activity activity,String key,long value){
		getSP(activity).edit().putLong(key, value).commit();
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void putStrnigSet(Activity activity,String key,Set<String> values){
		getSP(activity).edit().putStringSet(key, values).commit();
	}
	
	/**************************************************** remove ****************************************************/
	public static void removeKey(Activity activity,String key){
		getSP(activity).edit().remove(key).commit();
	
	}
	
	public static void clear(Activity activity){
		getSP(activity).edit().clear().commit();
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static void apply(Activity activity){
		getSP(activity).edit().apply();
		getSP(activity).edit().commit();
	}
	
	public static void commit(Activity activity){
		getSP(activity).edit().commit();
	}
	
	public static boolean contains(Activity activity,String key){
		return getSP(activity).contains(key);
	}
	
	public static void registerOnSharedPreferenceChangeListener(Activity activity,OnSharedPreferenceChangeListener onSharedPreferenceChangeListener){
		getSP(activity).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
	}
	
	public static void unregisterOnSharedPreferenceChangeListener(Activity activity,OnSharedPreferenceChangeListener onSharedPreferenceChangeListener){
		getSP(activity).unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
	}
}
