package com.umbrella.game.ubsdk.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
	/**
	 * 判断字符串是否包含中文字符
	 * @param str
	 * @return
	 */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    /**
     * 判断字符串是否为空或为null
     * @param str
     * @return
     */
    public static boolean isEmpty( CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
    
	/**
	 * 去除字符串中的所有字符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str){
	       String dest = null;
	       if(str == null){
	           return dest;
	       }else{
	           Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	           Matcher m = p.matcher(str);
	           dest = m.replaceAll("");
	           return dest;
	       }
	   }

	public static boolean equals(String str1,String str2){
		if (str1==null||str2==null) return false;
		return str1.equals(str2);
	}
	
	public static boolean equalsIgnoreCase(String str1,String str2){
		if (str1==null||str2==null) return false;
		return str1.equals(str2);
	}
}
