package com.neuralnoise.collect.entity.musei.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.collect.entity.Entity;
import com.neuralnoise.geo.GeoLocationUtils;
import com.neuralnoise.integration.geo.Location;

public class MuseiV3EntityParser {

	private static final Logger log = LoggerFactory.getLogger(MuseiV3EntityParser.class);

	public static Entity parse(Map<String, String> map) throws Exception {
		String type = "museum", name = null, description = null;
		Location location = null;

		final String sName = map.get("Nome Museo");
		if (sName != null && type != null) {
			name = sName;
		}

		final String desc = map.get("Breve descrizione"), categoria = map.get("Categoria"), orari = map.get("Orari"),
				email = map.get("Email"), tel = map.get("Telefono"), web = map.get("Sito web");
		if (desc != null && type != null) {
			description = (categoria != null ? "Categoria: " + categoria + " <br/> ": "");
			description += (web != null ? "Sito Web: <a href=\"" + web + "\">link</a> <br/> ": "");
			description += (desc != null ? "" + desc + " <br/> ": "");
			description += (orari != null ? "Orari: " + orari + " <br/> ": "");
			description += (tel != null ? "Tel: " + tel + " <br/> ": "");
			description += (email != null ? "E-Mail: " + email + " <br/> ": "");
		}

		final String address = map.get("Indirizzo"), prov = map.get("Provincia"), city = map.get("Città");
		if ((address != null || city != null) && type != null) {
			String str = (address != null ? address + ", " : ""); // + (prov != null ? prov + ", " : "") + (city != null ? city : "");
			str += ", Puglia";
			
			List<Location> locations = GeoLocationUtils.query(str, "it");
			
			if (locations.size() > 0) {
				location = locations.get(0);
			}
		}

		return new Entity(name, description, type, location);
	}
	
}
