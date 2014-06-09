package com.neuralnoise.collect.entity.dbunico;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class DBUnicoAdapter {

	public static void main(String[] args) throws Exception {
		final String address = "http://dbunico20.beniculturali.it/DBUnicoManagerWeb/dbunicomanager/searchPlace?regione=Puglia";
		URL url = new URL(address);

		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(is);

		int c = 100;
		
		while (reader.hasNext() && c-- > 0) {

			switch (reader.next()) {
			case XMLStreamConstants.START_ELEMENT: {
				String tagName = reader.getLocalName();
				// do anything with attributes, etc...
				System.out.println("tagName: " + tagName);	
			} break;
			
			case XMLStreamConstants.CHARACTERS: {
				String temp = reader.getText();
				// do something with element data
				System.out.println("data: " + temp);
			} break;
			
			case XMLStreamConstants.END_ELEMENT: {
				String tagName = reader.getLocalName();
				// decide if we should exit/clean up, etc...
				System.out.println("end element (" + tagName + ")");
			} break;
			}

		}
	}

}
