package com.plter.news.data;

import java.util.ArrayList;
import java.util.List;

public class Data {

	public static NewsData getNewsData(int index){
		return newsData.get(index);
	}
	
	public static List<NewsData> getAllNewsData(){
		return newsData;
	}
	
	public static int getNewsCatCount(){
		return newsData.size();
	}
	
	public static void setSelectedNews(int index){
		for (NewsData nd : newsData) {
			nd.setSelected(false);
		}
		
		getNewsData(index).setSelected(true);
	}
	
	private static List<NewsData> newsData = new ArrayList<NewsData>();
	
	static{
		newsData.add(new NewsData("热门", new String[]{"热门1","热门2","热门3","热门4","热门5"},true));
		newsData.add(new NewsData("科技", new String[]{"科技1","科技2","科技3","科技4","科技5"}));
		newsData.add(new NewsData("互联网", new String[]{"互联网1","互联网2","互联网3","互联网4","互联网5"}));
		newsData.add(new NewsData("金融", new String[]{"金融1","金融2","金融3","金融4","金融5"}));
		newsData.add(new NewsData("军事", new String[]{"军事1","军事2","军事3","军事4","军事5"}));
		newsData.add(new NewsData("房产", new String[]{"房产1","房产2","房产3","房产4","房产5"}));
	}
	
}
