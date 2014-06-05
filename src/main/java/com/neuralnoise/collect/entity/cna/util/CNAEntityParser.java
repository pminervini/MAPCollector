package com.neuralnoise.collect.entity.cna.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.collect.entity.Entity;
import com.neuralnoise.geo.GeoLocationUtils;
import com.neuralnoise.integration.geo.Location;

public class CNAEntityParser {

	private static final Logger log = LoggerFactory.getLogger(CNAEntityParser.class);

	private CNAEntityParser() { }

	public static Entity parse(Map<String, String> map) throws Exception {
		String type = null, name = null, description = null;
		Location location = null;
		
		final String category = map.get("categoria");
		if (category == null)
			return null;

		Pattern artisanPattern = Pattern.compile("Artigi|artigi|Resta|resta");
		Matcher artisanMatcher = artisanPattern.matcher(category);

		if (type == null && artisanMatcher.find()) {
			type = "artisan";
		}

		Pattern organizationPattern = Pattern.compile("Azienda|azienda|Agroalimentare|agroalimentare");
		Matcher organizationMatcher = organizationPattern.matcher(category);

		if (type == null && organizationMatcher.find()) {
			type = "organization";
		}

		final String sName = map.get("nome");
		if (sName != null && type != null) {
			name = sName;
		}

		final String sDescription = map.get("descrizione");
		if (sDescription != null && type != null) {
			description = sDescription;
		}

		final String address = map.get("indirizzo"), city = map.get("city");
		if ((address != null || city != null) && type != null) {
			String str = (address != null ? address + " " : "") + (city != null ? city : "");
			str += ", Puglia";
			
			List<Location> locations = GeoLocationUtils.query(str, "it");
			
			if (locations.size() > 0) {
				location = locations.get(0);
			}
		}

		return new Entity(name, description, type, location);
	}
}
