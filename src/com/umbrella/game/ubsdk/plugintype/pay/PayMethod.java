package com.umbrella.game.ubsdk.plugintype.pay;
/**
 * 自定义支付方式JavaBean封装 
 * @author qingshanliao
 */
public class PayMethod {
	/**
	 * 支付方式名字
	 */
	private String payName="";
	/**
	 * 支付方式的icon
	 */
	private int    payIcon=0;
	/**
	 * 产品名字
	 */
	private String productName="";
	/**
	 * 产品的价格
	 */
	private double amount=0.0;
	/**
	 * 产品描述
	 */
	private String productDesc="";
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public int getPayIcon() {
		return payIcon;
	}
	public void setPayIcon(int payIcon) {
		this.payIcon = payIcon;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PayMethod [payName=" + payName + ", payIcon=" + payIcon + ", productName=" + productName + ", amount="
				+ amount + ", productDesc=" + productDesc + "]";
	}

}
