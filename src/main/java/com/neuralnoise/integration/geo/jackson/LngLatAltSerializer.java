package com.neuralnoise.integration.geo.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.neuralnoise.integration.geo.LngLatAlt;

public class LngLatAltSerializer extends JsonSerializer<LngLatAlt> {

	@Override
	public void serialize(LngLatAlt value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeStartArray();
		jgen.writeNumber(value.getLongitude());
		jgen.writeNumber(value.getLatitude());
		if (value.hasAltitude())
			jgen.writeNumber(value.getAltitude());
		jgen.writeEndArray();
	}
}
