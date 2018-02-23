package com.umbrella.game.ubsdk.http;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;

public class UBHttpManager {
	private static UBHttpManager instance;
	private RequestQueue mQueue;
	
	private UBHttpManager(){
		mQueue = NoHttp.newRequestQueue();
	}
	
	public static UBHttpManager getInstance(){
		if (instance==null) {
			synchronized (UBHttpManager.class) {
				if (instance == null) {
					instance = new UBHttpManager();
				}
			}
		}
		return instance;
	}
	
	public <T> void addRequest(int what,Request<T> request,OnResponseListener<T> responseListener){
		mQueue.add(what, request, responseListener);
	}
	
    /**
     * 取消这个sign标记的所有请求。
     * @param sign 请求的取消标志。
     */
    public void cancelBySign(Object sign)
    {
    	mQueue.cancelBySign(sign);
    }

    /**
     * 取消队列中所有请求。
     */
    public void cancelAll()
    {
    	mQueue.cancelAll();
    }
}
