package com.umbrella.game.ubsdk.plugintype.pay;

/**
 * 支付配置
 * @author qingshanliao
 */
public class PayConfig {
	/**
	 * 产品唯一ID
	 */
	private int productID;
	/**
	 * 支付类型
	 */
	private int payType=PayConstant.PAY_TYPE_NORMAL;
	/**
	 * 计费点
	 */
	private Billing billing;
	/**
	 * 订单信息
	 */
	private UBOrderInfo orderInfo;
	/**
	 * 支付方式
	 */
	private PayMethod payMethod;
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
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
	public PayMethod getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}
	@Override
	public String toString() {
		return "PayConfig [productID=" + productID + ", payType=" + payType + ", billing=" + billing + ", orderInfo="
				+ orderInfo + ", payMethod=" + payMethod + "]";
	}
}
