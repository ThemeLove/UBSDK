package com.umbrella.game.ubsdk.plugintype.pay;

import java.util.ArrayList;
/**
 * 支付配置
 * @author qingshanliaos
 */
public class PayConfig {
	/**
	 * 产品唯一ID
	 */
	private String productID;
	/**
	 * 支付类型
	 */
	private int payType=PayType.PAY_TYPE_NORMAL;
	/**
	 * 计费点
	 */
	private Billing billing;
	/**
	 * 订单信息
	 */
	private UBOrderInfo orderInfo;
	
	/**
	 * 支付方法集合
	 */
	private ArrayList<PayMethodItem> payMethodItemList;
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public Billing getBilling() {
		return billing;
	}
	public void setBilling(Billing billing) {
		this.billing = billing;
	}
	public UBOrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(UBOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public ArrayList<PayMethodItem> getPayMethodItemList() {
		return payMethodItemList;
	}
	public void setPayMethodItemList(ArrayList<PayMethodItem> payMethodItemList) {
		this.payMethodItemList = payMethodItemList;
	}
	@Override
	public String toString() {
		return "PayConfig [productID=" + productID + ", payType=" + payType + ", billing=" + billing + ", orderInfo="
				+ orderInfo + ", payMethodItemList=" + payMethodItemList + "]";
	}
}
