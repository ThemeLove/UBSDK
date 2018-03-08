package com.umbrella.game.ubsdk.bean;

public class UBOrderInfo {
	private String goodsID="";
	private String goodsName="";
	private String goodsDesc="";
	private String quantifier="";
	private int count=1;
	private double amount=0;
	private String callbackUrl="";
	private String extrasParams="";
	private String cpOrderID="";
	private String cpOrderCreateTime="";
	private String orderID="";
	private String orderIdCreateTime="";
	private int payType=1;

	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getQuantifier() {
		return quantifier;
	}
	public void setQuantifier(String quantifier) {
		this.quantifier = quantifier;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getExtrasParams() {
		return extrasParams;
	}
	public void setExtrasParams(String extrasParams) {
		this.extrasParams = extrasParams;
	}

	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	public String getCpOrderID() {
		return cpOrderID;
	}
	public void setCpOrderID(String cpOrderID) {
		this.cpOrderID = cpOrderID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderIdCreateTime() {
		return orderIdCreateTime;
	}
	public void setOrderIdCreateTime(String orderIdCreateTime) {
		this.orderIdCreateTime = orderIdCreateTime;
	}
	
	public String getCpOrderCreateTime() {
		return cpOrderCreateTime;
	}
	public void setCpOrderCreateTime(String cpOrderCreateTime) {
		this.cpOrderCreateTime = cpOrderCreateTime;
	}
	
	public int getPayType() {
		return payType;
	}
	
	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	@Override
	public String toString() {
		return "UBOrderInfo [goodsId=" + goodsID + ", goodsName=" + goodsName + ", goodsDesc=" + goodsDesc
				+ ", quantifier=" + quantifier + ", count=" + count + ", amount=" + amount + ", callbackUrl="
				+ callbackUrl + ", extrasParams=" + extrasParams + ", cpOrderId=" + cpOrderID + ", OrderId=" + orderID
				+ "]";
	}
}
