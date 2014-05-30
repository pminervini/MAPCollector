package com.neuralnoise.integration;

import java.nio.charset.Charset;
import java.util.Arrays;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.util.ByteSequence;
import org.apache.commons.lang3.CharSet;
import org.apache.log4j.Logger;

public class RequestListener implements MessageListener {

	private static final Logger log = Logger.getLogger(RequestListener.class);

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				System.out.println(((TextMessage) message).getText());
			} catch (JMSException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			System.out.println("Message is not a TextMessage: " + message);
			
			ActiveMQObjectMessage amessage = (ActiveMQObjectMessage) message; 
			ByteSequence bs = amessage.getContent();
			String str = new String(bs.data, Charset.forName("ISO-8859-1"));
			System.out.println("Message: " + str);
			
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}

}
