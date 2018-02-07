package com.umbrella.game.ubsdk.model;

/**
 * UBSDK从网络获取数据的model  [初始化、登录、支付]
 * @author qingshanliao
 */
public class UBHttpModel {
	private static UBHttpModel instance=null;
	
	private UBHttpModel(){};
	
	public static UBHttpModel getInstance(){
		if (instance==null) {
			synchronized (UBHttpModel.class) {
				if (instance == null) {
					instance = new UBHttpModel();
				}
			}
		}
		return instance;
	}
	
	public void ubInit(){
		
	}
	
	public void ubLogin(){
		
	}
	
	public void ubPay(){
		
	}
	

	
}
