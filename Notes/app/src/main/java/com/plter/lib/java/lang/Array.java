package com.plter.lib.java.lang;


/**
 * 该数组使用链表实现，高效、安全
 * @author plter
 *
 * @param <T>
 */
public class Array<T> {


	public Array() {
		clear();
	}
	
	
	public final void each(ArrayLoopCallback<T> callback){
		
		ArrayItem<T> it = begin().nextItem();
		callback.setBreaked(false);
		
		while(it!=end()&&it!=null){
			callback.onRead(it.value(),it);
			if (callback.isBreaked()) {
				break;
			}
			
			it = it.nextItem();
		}
	}
	
	public final void reverseEach(ArrayLoopCallback<T> callback){
		
		ArrayItem<T> it = end().preItem();
		callback.setBreaked(false);
		
		while(it!=begin()&&it!=null){
			callback.onRead(it.value(),it);
			if (callback.isBreaked()) {
				break;
			}
			
			it = it.preItem();
		}
	}
	

	/**
	 * 根据索引获取一项
	 * @param index
	 * @return
	 */
	public final T get(int index){
		ArrayItem<T> i = getItem(index);
		return i!=null?i.value():null;
	}
	
	/**
	 * 判断数组中是否包含某项
	 * @param obj
	 * @return
	 */
	public final boolean contains(T obj){
		return indexOf(obj)>-1;
	}
	

	/**
	 * 获取某项所在的索引
	 * @param obj
	 * @return
	 */
	public final int indexOf(T obj){
		
		ArrayItem<T> tmp = _begin;
		
		for (int i = 0; i < length(); i++) {
			tmp = tmp.nextItem();
			
			if (tmp.value()==obj) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * 添加一项
	 * @param obj
	 * @return
	 */
	public final ArrayItem<T> push(T obj){
		return addItemBefore(new ArrayItem<T>(obj), _end);
	}

	/**
	 * 插入一项
	 * @param obj
	 * @param index
	 * @return
	 */
	public final ArrayItem<T> insert(T obj,int index){
		ArrayItem<T> tmp = getItem(index);
		if (tmp!=null) {
			return addItemBefore(new ArrayItem<T>(obj), tmp);
		}
		return null;
	}
	
	
	/**
	 * 删除最后一项
	 * @return
	 */
	public final T pop(){
		ArrayItem<T> tmp = _end.preItem();
		removeItem(tmp);
		return tmp.value();
	}
	
	
	/**
	 * 删除第一项
	 * @return
	 */
	public final T shift(){
		ArrayItem<T> tmp = _begin.nextItem();
		removeItem(tmp);
		return tmp.value();
	}

	/**
	 * 删除其中一项，建议使用removeItem提高程序运行效率
	 * @param obj
	 * @return
	 */
	public final T remove(T obj){
		
		ArrayItem<T> tmp = _begin;
		
		while(tmp.nextItem()!=null){
			tmp = tmp.nextItem();
			if (tmp.value()==obj) {
				removeItem(tmp);
				return obj;
			}
		}
		
		return null;
	}

	/**
	 * 删除指定索引处的该项
	 * @param index
	 * @return
	 */
	public final T remove(int index){
		ArrayItem<T> i = getItem(index);
		if (i!=null) {
			removeItem(i);
			return i.value();
		}
		return null;
	}

	public final void clear(){
		_begin.setNextItem(_end);
		_end.setPreItem(_begin);
		_length = 0;
	}

	public final int length(){
		return _length;
	}
	
	public final ArrayItem<T> begin(){
		return _begin;
	}
	
	public final ArrayItem<T> end(){
		return _end;
	}
	
	public ArrayItem<T> removeItem(ArrayItem<T> item){
		item.nextItem().setPreItem(item.preItem());
		item.preItem().setNextItem(item.nextItem());
		_length--;
		
		return item;
	}

	public ArrayItem<T> addItemBefore(ArrayItem<T> itemToAdd,ArrayItem<T> item){
		itemToAdd.setNextItem(item);
		itemToAdd.setPreItem(item.preItem());
		itemToAdd.preItem().setNextItem(itemToAdd);
		itemToAdd.nextItem().setPreItem(itemToAdd);
		_length++;
		return itemToAdd;
	}
	
	public ArrayItem<T> getItem(int index){
		
		if (index>=length()||index<0) {
			return null;
		}
		
		ArrayItem<T> tmp = null;
		
		if (index<length()/2) {
			tmp = _begin;
			for (int i = 0; i <= index; i++) {
				tmp = tmp.nextItem();
			}
		}else{
			tmp = _end;
			for (int i = length(); i > index; i--) {
				tmp = tmp.preItem();
			}
		}
		
		return tmp;
	}

	private int _length = 0;
	private final ArrayItem<T> _begin = new ArrayItem<T>(null);
	private final ArrayItem<T> _end = new ArrayItem<T>(null);
}
