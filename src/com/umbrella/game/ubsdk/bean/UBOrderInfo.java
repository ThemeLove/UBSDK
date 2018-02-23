package com.umbrella.game.ubsdk.bean;

public class UBOrderInfo {
	private String goodsId="";
	private String goodsName="";
	private String goodsDesc="";
	private String quantifier="";
	private int count;
	private double amount;
	private String callbackUrl="";
	private String extrasParams="";
	private String cpOrderId="";
	private String OrderId="";
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
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
	public String getCpOrderId() {
		return cpOrderId;
	}
	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	@Override
	public String toString() {
		return "UBOrderInfo [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsDesc=" + goodsDesc
				+ ", quantifier=" + quantifier + ", count=" + count + ", amount=" + amount + ", callbackUrl="
				+ callbackUrl + ", extrasParams=" + extrasParams + ", cpOrderId=" + cpOrderId + ", OrderId=" + OrderId
				+ "]";
	}
}
