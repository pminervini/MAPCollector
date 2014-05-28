package com.neuralnoise.activemq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	
	private final static String[] NAMES = { 
		//"topic",
		"topic.myTopic",
		//"topicChannel",
		//"listenerChannel"	
	};
	
	public static void main(String[] args) throws JMSException {
		for (String name : NAMES) {
			send(name);
		}
	}

	public static void send(final String name) throws JMSException {
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		// Create a Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		//Destination destination = session.createQueue(queueName);
		Destination destination = session.createTopic(name);

		// Create a MessageProducer from the Session to the Topic or Queue
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		// Create a messages
		String text = "Hello world! From: " + Thread.currentThread().getName();
		TextMessage message = session.createTextMessage(text);

		// Tell the producer to send the message
		System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
		producer.send(message);

		// Clean up
		session.close();
		connection.close();
	}

}
