package com.umbrella.game.ubsdk.callback;

public interface UBPayCallback {
	/**
	 * pay success
	 * @param cpOrderID			game's pay orderid
	 * @param orderID			channel's pay orderid
	 * @param goodsId			goods's id
	 * @param goodsName			goods's name
	 * @param goodsPrice		goods's price
	 * @param extrasParams		extra params
	 */
    public void onSuccess(String cpOrderID, String orderID,String goodsId,String goodsName,String goodsPrice, String extrasParams);

    /**
     * 支付取消
     * @param cpOrderID
     */
    public void onCancel(String cpOrderID);

    /**
     * 支付失败
     * @param cpOrderID
     * @param message
     * @param trace
     */
    public void onFailed(String cpOrderID, String message, String trace);
}
