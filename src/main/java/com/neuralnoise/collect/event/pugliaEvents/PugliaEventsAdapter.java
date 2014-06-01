package com.neuralnoise.collect.event.pugliaEvents;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.neuralnoise.collect.ICollectible;
import com.neuralnoise.collect.event.Event;
import com.neuralnoise.collect.rss.RSSCollectible;
import com.neuralnoise.collect.rss.RSSAdapter;
import com.neuralnoise.geo.GeoLocationUtils;
import com.neuralnoise.integration.geo.Location;

public class PugliaEventsAdapter extends RSSAdapter {

	private static final Logger log = LoggerFactory.getLogger(PugliaEventsAdapter.class);
	
	public PugliaEventsAdapter(String feed) {
		super(feed);
	}
	
	@Override
	public Collection<ICollectible> collect() throws Exception {
		final Collection<ICollectible> collectibles = super.collect();
		final Collection<ICollectible> events = Lists.newLinkedList();
		
		for (ICollectible collectible : collectibles) {
			final RSSCollectible rssEntry = (RSSCollectible) collectible;
			
			final String title = rssEntry.getTitle(), description = rssEntry.getDescription();
			
			final int idx = description.indexOf(" - ");
			String sDates = description.substring(0, idx);
			String sAddress = description.substring(idx + 3, description.indexOf('\n') - 1);
			
			SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.ITALY);
			final int idxDate = sDates.indexOf(" al "), lenDate = sDates.length();
			
			Date dStartDate = null, dEndDate = null;
			
			if (idxDate < 0) {
				dStartDate = format.parse(sDates);
				dEndDate = dStartDate;
			} else {
				dStartDate = format.parse(sDates.substring(5, idxDate) + sDates.substring(lenDate - 5, lenDate));
				dEndDate = format.parse(sDates.substring(idxDate + 4, lenDate));
			}
			
			Calendar startDate = new GregorianCalendar(), endDate = new GregorianCalendar();
			startDate.setTime(dStartDate);
			endDate.setTime(dEndDate);
			
			Location location = null;
			List<Location> locations = GeoLocationUtils.query(sAddress + ", Puglia", "it");
			if (locations != null && locations.size() > 0) {
				location = locations.get(0);
			}
			
			Event event = new Event(title, startDate, endDate, description, location);
			events.add(event);
		}
		
		return events;
	}
	
	public static void main(String[] args) throws Exception {
		PugliaEventsAdapter pe = new PugliaEventsAdapter("http://www.pugliaevents.it/it/feeds/category/4");
		Collection<ICollectible> events = pe.collect();
		for (ICollectible event : events) {
			System.out.println("Event: " + event);
		}
	}

}
