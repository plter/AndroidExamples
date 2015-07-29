package com.plter.lib.java.lang;


public final class ArrayItem<ValueType>{
	
	public ArrayItem(ValueType value) {
		this.setValue(value);
	}
	
	public ArrayItem<ValueType> preItem() {
		return preItem;
	}
	
	void setPreItem(ArrayItem<ValueType> preItem) {
		this.preItem = preItem;
	}

	public ArrayItem<ValueType> nextItem() {
		return nextItem;
	}

	void setNextItem(ArrayItem<ValueType> nextItem) {
		this.nextItem = nextItem;
	}

	public ValueType value() {
		return value;
	}

	void setValue(ValueType value) {
		this.value = value;
	}

	private ValueType value=null;
	private ArrayItem<ValueType> preItem = null;
	private ArrayItem<ValueType> nextItem = null;
}