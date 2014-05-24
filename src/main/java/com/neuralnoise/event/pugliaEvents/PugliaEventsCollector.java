package com.neuralnoise.event.pugliaEvents;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.collect.rss.RSSCollectible;
import com.neuralnoise.collect.rss.RSSCollector;

public class PugliaEventsCollector extends RSSCollector {

	private static final Logger log = LoggerFactory.getLogger(PugliaEventsCollector.class);
	
	public PugliaEventsCollector(String feed) {
		super(feed);
	}
	
	@Override
	public Collection<ICollectible> collect() throws Exception {
		final Collection<ICollectible> entries = super.collect();
		
		for (ICollectible entry : entries) {
			final RSSCollectible rssEntry = (RSSCollectible) entry;
			
			final String title = rssEntry.getTitle(), description = rssEntry.getDescription();
			
			final int idx = description.indexOf(" - ");
			String sDates = description.substring(0, idx);
			String sAddress = description.substring(idx + 3, description.indexOf('\n') - 1);
			
			log.info("Title: " + title);
			log.info("sDates: " + sDates);
			log.info("sAddress: " + sAddress);
			
			SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.ITALY);
			final int idxDate = sDates.indexOf(" al "), lenDate = sDates.length();
			
			Date startDate = null, endDate = null;
			
			if (idxDate < 0) {
				startDate = format.parse(sDates);
				endDate = startDate;
			} else {
				startDate = format.parse(sDates.substring(5, idxDate) + sDates.substring(lenDate - 5, lenDate));
				endDate = format.parse(sDates.substring(idxDate + 4, lenDate));
			}
			
			log.info("Start: " + startDate + ", End: " + endDate);
		}
		
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		PugliaEventsCollector pe = new PugliaEventsCollector("http://www.pugliaevents.it/it/feeds/category/4");
		pe.collect();
	}

}
