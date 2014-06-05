package com.neuralnoise.collect.event.discoveringPuglia.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DiscoveringPugliaUtils {

	private static final Logger log = LoggerFactory.getLogger(DiscoveringPugliaUtils.class);

	private DiscoveringPugliaUtils() { }
	
	public static List<Map<String, String>> retrieve(final String resource) throws Exception {
		//Document doc = Jsoup.connect("http://discovering.viaggiareinpuglia.it/").get();
		Document doc = Jsoup.connect(resource).timeout(64 * 1000).get();
		Elements scripts = doc.select("script");

		List<Map<String, String>> elements = Lists.newLinkedList();

		for (Element script : scripts) {
			String sscript = script.toString();
			String[] strs = StringUtils.split(sscript, "\n");

			for (String str : strs) {
				if (str.contains("var markers = ")) {
					int idx = str.indexOf('{');
					String sjson = str.substring(idx);
					JSONObject jobj = (JSONObject) JSONValue.parse(sjson); // oggetto

					for (Entry<String, Object> category : jobj.entrySet()) {
						final String type = category.getKey();
						JSONArray array = (JSONArray) category.getValue(); // array

						for (Object obj : array) {
							JSONObject elem = (JSONObject) obj; // e.g. masseria

							final String htmlMessage = elem.get("message").toString();
							Document docMessage = Jsoup.parse(htmlMessage);
							
							final String description = docMessage.select("a").text();
							final String address = docMessage.select("span").text();
							
							HashMap<String, String> map = Maps.newHashMap();

							map.put("type", type);
							map.put("description", description);
							map.put("address", address);
							
							for (Entry<String, Object> tmp : elem.entrySet()) {
								map.put(tmp.getKey(), tmp.getValue().toString());
							}
							
							elements.add(map);
						}
					}
				}
			}
		}
		
		/*
		StringBuffer sb = new StringBuffer();
		String NL = System.getProperty("line.separator");
		for (Map<String, String> element : elements) {
			sb.append(element.toString() + NL);
		}
		FileUtils.write(new File("e.txt"), sb.toString());
		*/
		
		return elements;
	}
	
	public static void main(String[] args) throws Exception {
		retrieve("http://discovering.viaggiareinpuglia.it/");
	}

}
