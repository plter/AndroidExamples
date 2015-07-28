package com.plter.lib.java.xml;

import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML对象，用于简化以Dom方式解析XML的操作
 * @author xtiqin
 */
public class XML {

	/**
	 * 根据Node对象构建XML对象
	 * @param node
	 */
	public XML(Node node) {
		this.node = node;
		
		//XMLRoot 类重写了getNode方法，如果当前的对象是XMLRoot实例的话，则node变量会被赋值为xml的根节点
		node=getNode();

		if (node!=null) {
			NodeList children = node.getChildNodes();
			Node child=null;
			if (children!=null) {
				int len= children.getLength();
				if (len>0) {
					childrenMap=new HashMap<String, XML>();

					for (int i = 0; i < len; i++) {
						child=children.item(i);

						if (!childrenMap.containsKey(child.getNodeName())) {
							childrenMap.put(child.getNodeName(), new XML(child));							
						}else{
							
							XML x=childrenMap.get(child.getNodeName());
							
							if (x instanceof XMLList) {
								((XMLList) x).add(new XML(child));
							}else{
								XMLList xmllist = new XMLList();
								childrenMap.put(child.getNodeName(), xmllist);
								xmllist.add(x);
								xmllist.add(new XML(child));
							}
						}
					}
				}
			}
		}
	}


	/**
	 * 取得节点的属性值
	 * @param name	属性的名字
	 * @param node	节点
	 * @return
	 */
	public String getAttr(String name){
		return getNode()!=null?getNode().getAttributes().getNamedItem(name).getNodeValue():null;
	}


	/**
	 * 取得当前节点的文本值
	 * @return
	 */
	public String getText(){
		return getNode()!=null?getNode().getTextContent():null;
	}


	/**
	 * 取得子项哈希图
	 * @return
	 */
	public HashMap<String, XML> getChildrenMap(){
		return childrenMap;
	}


	/**
	 * 根据标记取得子对象
	 * @param tag
	 * @return
	 */
	public XML getChild(String tag){
		return getChildrenMap()!=null?getChildrenMap().get(tag):null;
	}

	
	/**
	 * 取得标记名称
	 * @return
	 */
	public String getName(){
		return getNode()!=null?getNode().getNodeName():null;
	}


	/**
	 * 取得当前xml对象所关联的节点对象
	 * @return
	 */
	public Node getNode() {
		return node;
	}

	private Node node=null;
	private HashMap<String, XML> childrenMap=null;
}
