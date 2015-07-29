package com.plter.lib.java.utils;

public class NumTool {

	/**
	 * 把字节数单位转变成兆单位，并且精确到小数点后两位
	 */
	public static double bytesToMB(long size) {
		return ((double)((int)((((double)size)/1024/1024)*100)))/100;
	}
	
	/**
	 * 把字节数单位转变成兆单位，并且精确到小数点后两位
	 */
	public static double bytesToKB(long size) {
		return ((double)((int)((((double)size)/1024)*100)))/100;
	}

}
