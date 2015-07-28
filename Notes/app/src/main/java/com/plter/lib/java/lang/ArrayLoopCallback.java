package com.plter.lib.java.lang;

public abstract class ArrayLoopCallback<T> {
	public abstract void onRead(T currentValue,ArrayItem<T> currentItem);
	
	/**
	 * 跳出循环
	 */
	public final void break_(){
		breaked = true;
	}
	
	public boolean isBreaked() {
		return breaked;
	}
	
	void setBreaked(boolean breaked){
		this.breaked = breaked;
	}
	
	private boolean breaked = false;
}