package com.umbrella.game.ubsdk.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 单例Toast类
 * @author qingshanliao
 */
public class ToastUtil {
	private static Toast toast;
	
	/**
	 * 显示Toast
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context,String msg){
		if (toast==null) {
			toast=Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		}
//		toast.cancel();
		toast.setText(msg);
		toast.show();
	}
}
