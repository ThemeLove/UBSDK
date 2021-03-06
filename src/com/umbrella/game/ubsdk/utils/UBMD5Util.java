package com.umbrella.game.ubsdk.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import android.annotation.SuppressLint;
import android.util.Log;


public class UBMD5Util
{
    private  static String MD5_secretKey = "2aaa08de964854800c204e400006f45b";

    private static final String TAG = UBMD5Util.class.getSimpleName() + "Util";

    // MD5加密String[]数组
    public static String MD5EncryptString(String[] originalStringArray, String md5_secretKey)
    {
        MD5_secretKey = md5_secretKey;
        if (originalStringArray == null)
            return null;
        else
        {
            byte[] result = null;
            try
            {
                result = MessageDigest.getInstance("MD5").digest(
                        getOrderedStringFromArray(originalStringArray).getBytes("UTF-8"));
                Log.e("sign", getOrderedStringFromArray(originalStringArray).getBytes("UTF-8") + "--sign:"
                        + toHexString(result));
                // System.out.println("###>>>param.toArray=" +
                // getOrderedStringFromArray(originalStringArray));
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e)
            {

                e.printStackTrace();
            }

            return toHexString(result);
        }

    }

    public static String MD5EncryptString1(String s)
    {

        byte[] result = null;
        try
        {
            result = MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"));
            // Log.e("sign", s + "--sign_test:" + toHexString(result));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {

            e.printStackTrace();
        }

        return toHexString(result);
    }

    // MD5加密String[]数组
    public static String MD5EncryptString(TreeMap<String, String> urlParam, String md5_secretKey)
    {
    	MD5_secretKey = md5_secretKey;
        if (urlParam == null || urlParam.size() < 1)
            return null;
        else
        {
            byte[] result = null;
            try
            {
                result = MessageDigest.getInstance("MD5").digest(
                        (getOrderedStringParanFromTreeMap(urlParam) + md5_secretKey).getBytes("UTF-8"));
                UBLogUtil.logI("MD5 before:" + (getOrderedStringParanFromTreeMap(urlParam) + md5_secretKey) + "\n" + " SIGN:"
                        + toHexString(result));
                // System.out.println("###>>>>>" +
                // getOrderedStringParanFromTreeMap(urlParam) +
                // md5_secretKey);
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e)
            {

                e.printStackTrace();
            }

            return toHexString(result);
        }

    }

    // 气泡排序数组
    private static String getOrderedStringFromArray(String[] originalStringArray)
    {
        BubbleSort(originalStringArray, 0);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < originalStringArray.length; i++)
        {
            result.append(originalStringArray[i]);
            if (i != originalStringArray.length - 1)
            {
                result.append("&");
            }
            else
            {
                result.append(MD5_secretKey);
            }

        }

        return result.toString();
    }

    private static String getOrderedStringParanFromTreeMap(TreeMap<String, String> treeMap)
    {
        StringBuilder builder = new StringBuilder();
        for (String key : treeMap.keySet())
        {
            builder.append(key + "=");
            builder.append(treeMap.get(key));
            builder.append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        UBLogUtil.logI(TAG, builder.toString());
        return builder.toString();

    }

    // md5加密string
    public static String MD5EncryptString(String originalStringArray)
    {
        if (originalStringArray == null)
            return null;
        else
        {
            byte[] result = null;
            try
            {
                result = MessageDigest.getInstance("MD5").digest(originalStringArray.getBytes("UTF-8"));
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e)
            {

                e.printStackTrace();
            }

            return toHexString(result);
        }

    }

    // 按index位置字母进行气泡排序
    private static void BubbleSort(String[] sortStringArray, int index)
    {
        if (sortStringArray != null && sortStringArray.length > 0)
        {

            for (int i = 0; i < sortStringArray.length - 1; i++)
            {//
                for (int j = 0; j < sortStringArray.length - i - 1; j++)
                {
                    int minindex = sortStringArray[j].length() - 1 > sortStringArray[j + 1].length() - 1 ? sortStringArray[j + 1].length() - 1
                            : sortStringArray[j].length() - 1;
                    if (index <= minindex)
                    {
                        if ((int) (sortStringArray[j].charAt(index)) >= (int) (sortStringArray[j + 1].charAt(index)))
                        {
                            if ((int) (sortStringArray[j].charAt(index)) > (int) (sortStringArray[j + 1].charAt(index)))
                            {
                                String temp = sortStringArray[j];
                                sortStringArray[j] = sortStringArray[j + 1];
                                sortStringArray[j + 1] = temp;
                            }
                            else
                            {
                                String[] rebubble = {sortStringArray[j], sortStringArray[j + 1]};
                                int index2 = index + 1;

                                int maxindex = sortStringArray[j].length() - 1 > sortStringArray[j + 1].length() - 1 ? sortStringArray[j].length() - 1
                                        : sortStringArray[j + 1].length() - 1;
                                if (index2 <= maxindex)
                                {
                                    BubbleSort(rebubble, index2++);
                                    sortStringArray[j] = rebubble[0];
                                    sortStringArray[j + 1] = rebubble[1];
                                }

                            }

                        }
                    }
                }
            }
        }
    }

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};

    public static String toHexString(byte[] b)
    {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++)
        {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }
    
    
    
    @SuppressLint("DefaultLocale")
	public final static String encode(String s) {
        String encode = "";
        UBLogUtil.logI("MD5 before :" + s);
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            encode = new String(str).toLowerCase();
            UBLogUtil.logI("MD5 after :" + encode);
            return encode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

}
