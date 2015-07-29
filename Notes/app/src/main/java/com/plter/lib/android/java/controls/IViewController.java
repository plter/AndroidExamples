package com.plter.lib.android.java.controls;


/**
 * ViewController接口
 * @author xtiqin
 *
 */
public interface IViewController {

	
	/**
	 * 向堆栈中加入一个控制器对象，如果该控制器对象已经存在下一个对象，则不执行任何操作
	 * @param vc
	 * @param animated
	 */
	public void pushViewController(ViewController vc,boolean animated);
	
	/**
	 * 从堆栈中移除当前控制器对象的所以后续控制器对象
	 * @param animated 是否呈现动画效果
	 */
	public void popViewController(boolean animated);
}
