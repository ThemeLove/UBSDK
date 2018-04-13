package com.umbrella.game.ubsdk.ui;

import java.lang.reflect.Method;

public class Father {
	public static final String TAG=Father.class.getSimpleName();
	
	public void methodA(String str,Integer num){
		System.out.println("xxx");
		System.out.println("class="+getClass().getSimpleName()+",str="+str+",num="+num);
	}
	
	public boolean isSupportMethod(String methodName,Object[] args) {
		 System.out.println(TAG+"----->isSupportMethod");
        Class<?> [] parameterTypes=null;
        if (args!=null&&args.length>0) {
        	parameterTypes=new Class<?>[]{ };
			for(int i=0;i<args.length;i++){
				parameterTypes[i]=args[i].getClass();
			}
		}
        
        try {
			Method method = getClass().getMethod(methodName, parameterTypes);
			return method==null?false:true;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Object callMethod(String methodName, Object[] args) {
		 System.out.println(TAG+"----->callMethod");
		Class<?>[] paramterTypes=null;
		if (args!=null&&args.length>0) {
			paramterTypes=new Class<?>[]{};
			for (int i=0;i<args.length;i++) {
				paramterTypes[i]=args[i].getClass();
			}
		}
		
		try {
			Method method = getClass().getMethod(methodName, paramterTypes);
			return method.invoke(this, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
