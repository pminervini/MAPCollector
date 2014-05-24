package com.neuralnoise.collect.rss;

import com.neuralnoise.collect.ICollectible;

public class RSSCollectible implements ICollectible {
	
	protected final String title;
	protected final String description;
	
	public RSSCollectible(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}

}
