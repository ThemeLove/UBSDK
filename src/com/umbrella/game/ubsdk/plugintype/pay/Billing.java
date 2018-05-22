package com.umbrella.game.ubsdk.plugintype.pay;

/**
 * 计费点
 * @author lqs
 */
public class Billing {
	/**
	 * 商品ID
	 */
	private String ProductID;
	/**
	 * 计费点ID
	 */
	private String BillingID;
	/**
	 * 计费点名字
	 */
	private String BillingName;
	/**
	 * 计费点价格
	 */
	private String BillingPrice;
	public String getProductID() {
		return ProductID;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}
	public String getBillingID() {
		return BillingID;
	}
	public void setBillingID(String billingID) {
		BillingID = billingID;
	}
	public String getBillingName() {
		return BillingName;
	}
	public void setBillingName(String billingName) {
		BillingName = billingName;
	}
	public String getBillingPrice() {
		return BillingPrice;
	}
	public void setBillingPrice(String billingPrice) {
		BillingPrice = billingPrice;
	}
	@Override
	public String toString() {
		return "Billing [ProductID=" + ProductID + ", BillingID=" + BillingID + ", BillingName=" + BillingName
				+ ", BillingPrice=" + BillingPrice + "]";
	}
}
