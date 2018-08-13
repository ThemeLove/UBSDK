package com.umbrella.game.ubsdk.utils;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * Android权限工具类
 * @author qingshanliao
 */
public class PermissionUtil {
	private static final String TAG=PermissionUtil.class.getSimpleName();
	
	/**
	 * 动态申请权限
	 * @param activity
	 * @param requestCode
	 * @param permissionList
	 * @param permissionCallback
	 */
	public static void dynamicApplyPermission(Activity activity,int requestCode,List<String> permissionList,PermissionCallback permissionCallback){
		UBLogUtil.logI(TAG+"----->dynamicApplyPermission");
		List<String> notGrantedPermissionList = checkPermissions(activity, permissionList);
		if (notGrantedPermissionList!=null&&notGrantedPermissionList.size()>0) {//有没有获得权限，要再次申请
			applyPermissions(activity,requestCode,notGrantedPermissionList);//
		}else{//已经拥有申请的权限,回调给调用者
			if (permissionCallback!=null) {
				permissionCallback.haveGrantedPermission();
			}
		}
	}
	
	/**
	 * 检查是否有指定权限集合 
	 * @param activity
	 * @param checkPermissionList
	 * @return 返回null,说明拥有所有权限；非null，返回尚未拥有权限集合
	 */
	@TargetApi(Build.VERSION_CODES.M)
	private static List<String> checkPermissions(Activity activity,List<String> checkPermissionList){
		ArrayList<String> requestPermissionList =new ArrayList<String> ();
		for (String checkPermission : checkPermissionList) {
			if (!(activity.checkSelfPermission(checkPermission)==PackageManager.PERMISSION_GRANTED)) {
				requestPermissionList.add(checkPermission);
			}
		}
		return requestPermissionList.size()>0?requestPermissionList:null;
	}
	
	/**
	 * 申请权限
	 * @param activity
	 * @param requestCode
	 * @param requestPermissionList
	 */
	@TargetApi(Build.VERSION_CODES.M)
	private static void applyPermissions(Activity activity,int requestCode,List<String> requestPermissionList){
		if (requestPermissionList!=null&&requestPermissionList.size()>0) {
			activity.requestPermissions((String[]) requestPermissionList.toArray(), requestCode);
		}
	}

	
/***************************************************handle***************************************************/
	 
	 /**
	  * 处理动态申请权限结果
	  * @param activity
	  * @param grantResults
	  * @param permissionCallback
	  */
	 @TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static void dynamicHandleApplyPermissionResult(Activity activity,int[] grantResults,PermissionCallback permissionCallback){
		 UBLogUtil.logI(TAG+"----->dynamicHandleApplyPermissionResult");
		 if (hasAllPermissionsGranted(grantResults)) {//已经获得所有申请的权限，回调给调用者
			if (permissionCallback!=null) {
				permissionCallback.haveGrantedPermission();
			}
		 }else{
	        // 如果用户没有授权，那么应该说明意图，引导用户去设置里面授权。
	    	ToastUtil.showToast(activity, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。");
	        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
	        intent.setData(Uri.parse("package:" + activity.getPackageName()));
	        activity.startActivity(intent);
		}
	 }
	 
	 /**
	  * 权限是否获取成功
	  * @param grantResults
	  * @return
	  */
	 private static boolean hasAllPermissionsGranted(int[] grantResults) {
		    for (int grantResult : grantResults) {
		      if (grantResult == PackageManager.PERMISSION_DENIED) {
		        return false;
		      }
		    }
		    return true;
	 }
	 
	 
	 /**
	  * 申请权限结果
	  * @author qingshanliao
	  */
	 public interface PermissionCallback{
		 /**
		  * 已经被授权
		  */
		 void haveGrantedPermission();
	 }
}
