package com.umbrella.game.ubsdk.ui;

public class Test {
	public static void main(String[] args) {
		Father father = new Father();
		father.methodA("father",1);
		Son son = new Son();
//		son.methodA("son",2);
		boolean supportMethod = son.isSupportMethod("methodA",new Object[]{"son",2});
		System.out.println("isSupportMethod="+supportMethod);
		son.callMethod("methodA",new Object[]{"son",2});
	}
}
