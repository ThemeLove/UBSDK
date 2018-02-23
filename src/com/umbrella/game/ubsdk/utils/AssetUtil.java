package com.umbrella.game.ubsdk.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

import android.content.Context;

/**
 * asset工具类
 * @author qingshanliao
 */
public class AssetUtil {
	/**
	 * 读取Android 工程asset目录下指定文件名的文件，以字符串的形式返回其内容。
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String getAssetConfigStr(Context context,String fileName){
		StringBuffer sb = null;
		InputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			is = context.getAssets().open(fileName);
			isr = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader(isr);
			sb = new StringBuffer();
			String str="";
			while ((str=br.readLine())!=null) {
				sb.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
				try {
					if (is!=null)is.close();
					if (isr!=null)isr.close();
					if (br!=null)br.close();
					is=null;
					isr=null;
					br=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return sb==null?null:sb.toString();
	}
	
	/**
	 * 读取Android工程asset目录下指定文件名的文件[加过密]，义字符串的形式返回其内容
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String getAssetDESConfigStr(Context context,String fileName){
		StringBuffer sb =null;

		ObjectInputStream objis=null;
		CipherInputStream cis=null;
		InputStreamReader isr=null;
		BufferedReader br =null;
		try {
			sb = new StringBuffer();
			objis = new ObjectInputStream(context.getAssets().open("ubsdk.dat"));

//			获取打包脚本动态生成的解密key
			Key key = (Key) objis.readObject();
//			加解密有用Cipher来实现
			Cipher cipher = Cipher.getInstance("DESede");
            // 输入流  
			cipher.init(Cipher.DECRYPT_MODE, key);
			cis = new CipherInputStream(new BufferedInputStream(context.getAssets().open(fileName)), cipher);
			isr = new InputStreamReader(cis);
			br = new BufferedReader(isr);
			String str="";
			while ((str=br.readLine())!=null) {
				sb.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (objis!=null) objis.close();
				if(cis!=null)cis.close();
				if(isr!=null)isr.close();
				if(br!=null)br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb==null?null:sb.toString();
	}
}
