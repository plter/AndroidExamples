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

package com.plter.lib.java.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * XML解析工具
 * @author xtiqin
 */
public class XMLRoot extends XML{

	/**
	 * 解析一个xml文件
	 * @param f
	 * @return
	 */
	public static XMLRoot parse(File f){
		try {
			return new XMLRoot(f) {
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 解析输入流
	 * @param is
	 * @return
	 */
	public static XMLRoot parse(InputSource is){
		try {
			return new XMLRoot(is) {
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析输入流
	 * @param is
	 * @return
	 */
	public static XMLRoot parse(InputStream is,String enc){
		try {
			return new XMLRoot(is,enc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析输入源
	 * @param is
	 * @return
	 */
	public static XMLRoot parse(InputStream is){
		try {
			return new XMLRoot(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 解析xml文本
	 * @param xml
	 * @return
	 */
	public static XMLRoot parse(String xml){
		try {
			return new XMLRoot(xml) {
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//constructor>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private XMLRoot(File f) throws SAXException, IOException, ParserConfigurationException{
		super(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f));
	}
	private XMLRoot(InputSource is) throws SAXException, IOException, ParserConfigurationException{
		super(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is));
	}
	private XMLRoot(InputStream is) throws SAXException, IOException, ParserConfigurationException{
		super(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is));
	}
	private XMLRoot(InputStream is,String enc) throws SAXException, IOException, ParserConfigurationException{
		this(new InputSource(new InputStreamReader(is, enc)));
	}
	private XMLRoot(InputStream is,Charset enc) throws SAXException, IOException, ParserConfigurationException{
		this(new InputSource(new InputStreamReader(is, enc)));
	}
	private XMLRoot(String xml) throws SAXException, IOException, ParserConfigurationException{
		this(new InputSource(new StringReader(xml)));
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**
	 * 取得该XML的Document对象
	 * @return
	 */
	public Document getDocument(){
		return (Document) super.getNode();
	}

	/**
	 * 根据标签名称取得节点列表
	 * @param tagName	标签名称
	 * @return
	 */
	public NodeList getElementsByTagName(String tagName){
		return getDocument().getElementsByTagName(tagName);
	}

	/**
	 * 根据命名空间取得节点列表
	 * @param namespaceURI
	 * @param localName
	 * @return
	 */
	public NodeList getElementsByTagNameNS(String namespaceURI,String localName){
		return getDocument().getElementsByTagNameNS(namespaceURI, localName);
	}

	/**
	 * 根据ID取得节点
	 * @param elementId 节点id
	 * @return
	 */
	public Element getElementById(String elementId){
		return getDocument().getElementById(elementId);
	}
	
	public Node getNode() {
		return ((Document) super.getNode()).getDocumentElement();
	}
}

