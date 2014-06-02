package com.neuralnoise.integration.geo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Location {

	private static final Logger log = LoggerFactory.getLogger(Location.class);

	protected String name;
	protected Point point;

	public Location() { }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", point=" + point + "]";
	}

}
