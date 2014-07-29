/**
   Copyright [plter] [xtiqin]
   http://plter.sinaapp.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   This is a part of PlterAndroidLib on 
   http://plter.sinaapp.com/?cat=14
*/

package com.plter.lib.android.java.controls;

import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.plter.lib.java.lang.Array;

public abstract class ArrayAdapter<T> extends BaseAdapter{

	public ArrayAdapter(Context context, int layoutId, T[] objects) {
		this.layoutId=layoutId;
		this.context=context;
		
		for (T t : objects) {
			cellDataList.push(t);
		}
	}

	public ArrayAdapter(Context context, int layoutId, List<T> objects) {		
		this.layoutId=layoutId;
		this.context=context;
		
		
		for (T t : objects) {
			cellDataList.push(t);
		}
	}

	public ArrayAdapter(Context context, int layoutId) {		
		this.layoutId=layoutId;
		this.context=context;
	}
	
	
	public void removeFirst(){
		if (getCount()<=0) {
			return;
		}
		
		remove(0);
	}
	
	
	public void remove(int index){
		cellDataList.remove(index);
		
		notifyDataSetChanged();
	}
	
	public void removeLast(){
		if (getCount()<=0) {
			return;
		}
		
		remove(getCount()-1);
	}
	
	public void remove(T object){
		cellDataList.remove(object);
		
		notifyDataSetChanged();
	}
	
	public void add(T object){
		cellDataList.push(object);
		
		notifyDataSetChanged();
	}
	
	
	public void addAll(Collection<? extends T> col){
		for (T t : col) {
			cellDataList.push(t);
		}
		
		notifyDataSetChanged();
	}
	
	public void addAll(T[] arr){
		for (T t : arr) {
			cellDataList.push(t);
		}
		
		notifyDataSetChanged();
	}
	
	
	public void clear(){
		cellDataList.clear();
		
		notifyDataSetChanged();
	}
	
	
	public Context getContext() {
		return context;
	}

	
	public int getCount() {
		return cellDataList.length();
	}

	
	public T getItem(int position) {
		return cellDataList.get(position);
	}

	
	public long getItemId(int position) {
		return position;
	}
	
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null) {
			LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(layoutId, null);
		}
		
		initListCell(position, convertView, parent);
		return convertView;
	}
	
	
	/**
	 * 初始化列表项
	 * @param position
	 * @param convertView
	 * @param parent
	 */
	public abstract void initListCell(int position, View listCell, ViewGroup parent);

	//private values
	private int layoutId=0;
	
	private final Array<T> cellDataList=new Array<T>();
	
	private Context context=null;
	
}
