package com.plter.lib.android.java.controls;

import com.plter.lib.java.event.Event;


/**
 * ViewController相关的事件
 * @author xtiqin
 *
 */
public class ViewControllerEvent extends Event {
	
	
	/**
	 * 构建一个ViewControllerEvent事件
	 * @param type	事件的类型
	 */
	public ViewControllerEvent(String name) {
		super(name);
	}


	/**
	 * 将要后退事件，该事件可以取消
	 */
	public static final String BACKING="backing";
	
	/**
	 * 后退完成事件
	 */
	public static final String BACK="back";
	
	
	/**
	 * 导航到
	 */
	public static final String NAVIGATE_TO="navigateTo";
}
