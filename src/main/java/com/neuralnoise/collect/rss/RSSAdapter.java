package com.neuralnoise.collect.rss;

import java.net.URL;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.neuralnoise.collect.IAdapter;
import com.neuralnoise.collect.ICollectible;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RSSAdapter implements IAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(RSSAdapter.class);
	
	private final String feed;
	
	public RSSAdapter(String feed) {
		this.feed = feed;
	}
	
	public Collection<ICollectible> collect() throws Exception {
		URL url = new URL(String.format(feed, "it"));
		XmlReader reader = null;

		Collection<ICollectible> collectibles = Lists.newLinkedList();
		
		try {
			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);

			for (Object obj : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) obj;

				String title = entry.getTitle();
				String description = entry.getDescription().getValue();

				log.debug("Title: " + title);
				log.debug("Description: " + description);

				ICollectible collectible = new RSSCollectible(title, description);
				collectibles.add(collectible);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		return collectibles;
	}

}
