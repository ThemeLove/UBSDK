package com.umbrella.game.ubsdk.callback;

public interface UBPayCallback {

	/**
	 * 支付成功 paySuccess 
	 * @param  ubOrderID  umbrella pay orderid
	 * @param  cpOrderID
	 * @param  extrasParams
	 */
    public void onSuccess(String ubOrderID, String cpOrderID, String extrasParams);

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
