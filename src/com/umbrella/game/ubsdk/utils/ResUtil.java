package com.umbrella.game.ubsdk.utils;

import java.util.Random;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;

public class ResUtil {
	public static String getAppName(Context context){    
		String appName = null;
		try {
			String packname = context.getPackageName();
			PackageManager pm = context.getPackageManager();
			ApplicationInfo info = pm.getApplicationInfo(packname, 0);     
			appName = info.loadLabel(pm).toString();
		} catch(Exception e) {    
			e.printStackTrace();    
		}
		return appName;
	}
	
	private final static String DRAWABLE = "drawable";
	private final static String LAYOUT = "layout";
	private final static String ID = "id";
	private final static String COLOR = "color";
	private final static String DIMEN = "dimen";
	private final static String STRING = "string";
	private final static String STYLE = "style";
	private final static String ANIM = "anim";
	
	/**
     * 按资源名称查找资源名称
     * @param context
     * @param packageName
     * @param resourcesName
     * @return
     */
    private static int getResourcesIdByName(Context context, String packageName, String resourcesName) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(resourcesName, packageName, context.getPackageName());
        if(id == 0) {
            UBLogUtil.logE("fail to load resource:" + resourcesName);
        }
        return id;
    }
    
    /**
     * 根据动画文件的名称 返回对应的id
     * @param context
     * @param animName
     * @return
     */
    public static int getAnimId(Context context, String animName) {
    	return getResourcesIdByName(context, ANIM, animName);
    }
    
    /**
     * 获取drawable资源的id
     * @param context
     * @param resourcesName
     * @return
     */
    public static int getDrawableId(Context context, String drawableName) {
        return getResourcesIdByName(context, DRAWABLE, drawableName);
    }
    
    /**
     * 获取layout资源的id
     * 
     * @param context
     * @param layoutName
     * @return
     */
    public static int getLayoutId(Context context, String layoutName) {
        return getResourcesIdByName(context, LAYOUT, layoutName);
    }

    /**
     * 获取view的id
     * 
     * @param context
     * @param viewId
     * @return
     */
    public static int getViewID(Context context, String viewId) {
        return getResourcesIdByName(context, ID, viewId);
    }

    /**
     * 获取color资源的id
     * 
     * @param context
     * @param colorName
     * @return
     */
    public static int getColorId(Context context, String colorName) {
        return getResourcesIdByName(context, COLOR, colorName);
    }

    /**
     * 获取dimen资源的id
     * 
     * @param context
     * @param dimenName
     * @return
     */
    public static int getDimenId(Context context, String dimenName) {
        return getResourcesIdByName(context, DIMEN, dimenName);
    }

    /**
     * 获取String资源的id
     * 
     * @param context
     * @param stringName
     * @return
     */
    public static int getStringId(Context context, String stringName) {
        return getResourcesIdByName(context, STRING, stringName);
    }

    public static String getStringFormResouse(Context context, String stringName) {
        return context.getResources().getString(getResourcesIdByName(context, STRING, stringName));
    }

    /**
     * 获取style资源id
     * 
     * @param context
     * @param styleName
     * @return
     */
    public static int getStyleId(Context context, String styleName) {
        return getResourcesIdByName(context, STYLE, styleName);
    }
    
    public static String getMetaData(Context context, String key) {
    	try {
			ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			Object value = info.metaData.get(key);
			if(value != null) {
				return value.toString();
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static JSONObject toJSON(String data) {
        JSONObject result = null;
        try {
            result = new JSONObject(data);
        } catch (Exception e) {
        	e.printStackTrace();
        	UBLogUtil.logE("to json fail 【" + data + "】");
        }
        return result;
    }
    
    /**
     * 生成MD5的随机数值
     * @return
     */
    @SuppressLint("DefaultLocale")
	public static String getMD5RomdomStr(){
        String random = "";
        Random r = new Random();
        long ran = r.nextInt(10000) + 1;
        long time = System.currentTimeMillis();
        String beforeMd5 = String.valueOf(time) + String.valueOf(ran);
        random = UBMD5Util.encode(beforeMd5).toLowerCase();
        return random;
    }
}
