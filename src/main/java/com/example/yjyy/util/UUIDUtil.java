package com.example.yjyy.util;

public class UUIDUtil {

	public static String randomUUID() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] agrs){
		System.err.println(randomUUID());
	}
}
