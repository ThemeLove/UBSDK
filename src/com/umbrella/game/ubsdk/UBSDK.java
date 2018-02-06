package com.umbrella.game.ubsdk;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

public class UBSDK {
	public static UBSDK instance=null;
	private Handler mMainThreadHandler=null;
	private UBSDK(){
		mMainThreadHandler=new Handler(Looper.getMainLooper());
	};
	public static UBSDK getInstance(){
		if (instance==null) {
			synchronized (UBSDK.class) {
				if (instance == null) {
					instance = new UBSDK();
				}
			}
		}
		return instance;
	}
	
	private Activity mActivity;
	
	public void init(Activity activity){
		this.mActivity=activity;
		runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				
				
			}
		});
	}
	
	private void runOnUIThread(Runnable runnable){
		if (runnable==null) return;
		if (mMainThreadHandler!=null) {
			mMainThreadHandler.post(runnable);
		}else{
			mActivity.runOnUiThread(runnable);
		}
	}
	
}
