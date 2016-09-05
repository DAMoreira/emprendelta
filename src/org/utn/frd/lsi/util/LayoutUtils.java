package org.utn.frd.lsi.util;

public class LayoutUtils {

	public static String transformJSON(String json){

		json = json
				.replaceAll("class\"", "className\"")
				.replaceAll("data-news-section", "dataNewsSection")
				.replaceAll("data-news-type", "dataNewsType")
				.replaceAll("data-news-style", "dataNewsStyle")
				.replaceAll("data-contenttype", "dataContentType")
				.replaceAll("data-contentvalue", "dataContentValue")
				.replaceAll("data-type", "dataType");

		return json;
	}
	
	public static String transform2HTML(String layout) {

		layout = layout
				.replaceAll("className", "class")
				.replaceAll("dataType", "data-type")
				.replaceAll("dataNewsSection", "data-news-section")
				.replaceAll("dataNewsType", "data-news-type")
				.replaceAll("dataNewsStyle", "data-news-style")
				.replaceAll("dataContentType", "data-contenttype")
				.replaceAll("dataContentValue", "data-contentvalue");

		return layout;
	}

}
