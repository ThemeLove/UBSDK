package com.umbrella.game.ubsdk.model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;

import com.umbrella.game.ubsdk.config.UBSDKConfig;
import com.umbrella.game.ubsdk.plugintype.pay.Billing;
import com.umbrella.game.ubsdk.plugintype.pay.PayConfig;
import com.umbrella.game.ubsdk.plugintype.pay.PayMethod;
import com.umbrella.game.ubsdk.plugintype.pay.UBOrderInfo;
import com.umbrella.game.ubsdk.utils.AssetUtil;
import com.umbrella.game.ubsdk.utils.TextUtil;

import android.util.Xml;

public class UBPayConfigModel {
	private final static String TAG=UBPayConfigModel.class.getSimpleName();
	
	private UBPayConfigModel(){};
	
	private static UBPayConfigModel instance=null;
	
	public static UBPayConfigModel getInstance(){
		if (instance==null) {
			if (instance==null) {
				instance=new UBPayConfigModel();
			}
		}
		return instance;
	}
	
	/**
	 * 加载商店支付配置
	 * @return
	 */
	public HashMap<String,PayConfig> loadStorePayConfig(String payConfigPath){
		String payConfigStr = AssetUtil.getAssetConfigStr(UBSDKConfig.getInstance().getApplicationContext(), "payConfig.xml");
		if (TextUtil.isEmpty(payConfigStr))return null;
		HashMap<String, PayConfig> payConfigMap = new HashMap<>();
		
		PayConfig payConfig = null;
		ArrayList<PayMethod> payMethodList = null;
		XmlPullParser parse = Xml.newPullParser();
		
		try {
			parse.setInput(new StringReader(payConfigStr));
			int eventType = parse.getEventType();
			while (eventType!=XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					String name = parse.getName();
					if (TextUtil.equals("product",name)) {
						String productID = parse.getAttributeValue(null, "productID");
						String payType = parse.getAttributeValue(null, "payType");
						
						payConfig = new PayConfig();
						payConfig.setProductID(Integer.parseInt(productID));
						payConfig.setPayType(Integer.parseInt(payType));
						
						payConfigMap.put(productID, payConfig);
						
						payMethodList = new ArrayList<PayMethod>();
						payConfig.setPayMethodList(payMethodList);
					}else if(TextUtil.equals("billing",name)){
						Billing billing = new Billing();
						String billingID = parse.getAttributeValue(null, "billingID");
						String billingName = parse.getAttributeValue(null, "billingName");
						String billingPrice = parse.getAttributeValue(null, "billingPrice");
						billing.setBillingID(billingID);
						billing.setBillingName(billingName);
						billing.setBillingPrice(billingPrice);
						payConfig.setBilling(billing);
					}else if (TextUtil.equals("paymethod", name)) {
						PayMethod payMethod = new PayMethod();
						String payName = parse.getAttributeValue(null, "payName");
						String payIcon = parse.getAttributeValue(null, "payIcon");
						String amount = parse.getAttributeValue(null, "amount");
						String productName = parse.getAttributeValue(null, "productName");
						String productDesc = parse.getAttributeValue(null, "productDesc");
						payMethod.setAmount(Double.parseDouble(amount));
						payMethod.setPayIcon(Integer.parseInt(payIcon));
						payMethod.setPayName(payName);
						payMethod.setProductName(productName);
						payMethod.setProductDesc(productDesc);
						payMethodList.add(payMethod);
					}else if (TextUtil.equals("orderinfo", name)) {
						UBOrderInfo ubOrderInfo = new UBOrderInfo();
						String orderID = parse.getAttributeValue(null, "orderID");
						String amount = parse.getAttributeValue(null,"amount");
						String productName = parse.getAttributeValue(null, "productName");
						String productDesc = parse.getAttributeValue(null,"productDesc");
						String ext = parse.getAttributeValue(null, "ext");
						ubOrderInfo.setAmount(Double.parseDouble(amount));
						ubOrderInfo.setOrderID(orderID);
						ubOrderInfo.setGoodsName(productName);
						ubOrderInfo.setGoodsDesc(productDesc);
						ubOrderInfo.setExtrasParams(ext);
						payConfig.setOrderInfo(ubOrderInfo);
					}
					break;
				default:
					break;
				}
				
				eventType=parse.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payConfigMap;
	}
}
