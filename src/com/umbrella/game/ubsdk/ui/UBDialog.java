package com.umbrella.game.ubsdk.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Process;

public class UBDialog {
	public static AlertDialog showNormalDialog(Context context,String title,String message,String option1,DialogInterface.OnClickListener listener1,String option2,DialogInterface.OnClickListener listener2){
		AlertDialog dialog = new AlertDialog.Builder(context)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton(option1,listener1)
		.setNegativeButton(option2, listener2).show();
		return dialog;
	}
	
	public static void showExitDialog(final Activity activity){
		showNormalDialog(activity, "exit the game","Are you sure you want to quit?", "exit", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				activity.finish();
				System.exit(0);
				Process.killProcess(Process.myPid());
			}
		}, "cancel",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				dialog=null;
			}
		});
	}
	
}
