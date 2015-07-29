package com.plter.lib.java.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * XML列表
 * @author xtiqin
 */
public class XMLList extends XML {

	/**
	 * 构建一个XMLList对象
	 */
	public XMLList() {
		super(null);
	}
	
	void add(XML xml){
		getChildren().add(xml);
	}
	
	/**
	 * 取得指定位置的对象
	 * @param index
	 * @return
	 */
	public XML get(int index){
		return getChildren().get(index);
	}
	
	
	/**
	 * 获得XMLList对象的长度
	 * @return
	 */
	public int length(){
		return getChildren().size();
	}
	
	
	/**
	 * 取得子对象数组
	 * @return
	 */
	public List<XML> getChildren() {
		return children;
	}

	private final List<XML> children = new ArrayList<XML>();
}
