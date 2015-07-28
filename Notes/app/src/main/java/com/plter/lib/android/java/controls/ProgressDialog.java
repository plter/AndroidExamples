package com.plter.lib.android.java.controls;

import android.content.Context;

/**
 * 对系统的ProgressDialog进行封装，用以提供更加简单的调用方式
 * @author xtiqin
 *
 */
public class ProgressDialog {	
	
	private static android.app.ProgressDialog __dialog =null;
	
	
	/**
	 * 呈现一个等待提示框
	 * @param context	
	 * @param message	提示框中呈现的内容
	 * @param title		提示框的标题
	 */
	public static final void show(Context context,String message,String title){
		if (__dialog==null) {
			__dialog= android.app.ProgressDialog.show(context, title, message);
		}else{
			__dialog.setTitle(title);
			__dialog.setMessage(message);
		}
	}
	
	
	/**
	 * 呈现一个等待提示框
	 * @param context
	 * @param message	提示框中呈现的内容
	 */
	public static final void show(Context context,String message){
		show(context, message, "");
	}
	
	
	public static final void hide(){
		if (__dialog!=null) {
			__dialog.dismiss();
			__dialog=null;
		}
	}
}
