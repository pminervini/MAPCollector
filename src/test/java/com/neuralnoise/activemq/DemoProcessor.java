package com.neuralnoise.activemq;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuralnoise.listener.RequestHandler;
import com.neuralnoise.model.Request;
import com.neuralnoise.model.Request.RequestType;

public class DemoProcessor {

	public static void main(String[] args) {
		AbstractApplicationContext context =
			new ClassPathXmlApplicationContext("/META-INF/spring/collector-servlet.xml", DemoProcessor.class);

		RequestHandler cafe = (RequestHandler) context.getBean("collector");

		Request request = new Request("name", RequestType.CREATE, "body");
		cafe.placeRequest(request);
		
		context.close();
	}

	
}
