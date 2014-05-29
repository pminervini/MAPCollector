package com.neuralnoise.model;

import java.util.Map;

import com.google.common.collect.Maps;

public class Message {
	public enum MessageType {
		CREATE, UPDATE, DELETE
	}

	private String name;
	private MessageType messageType;
	private String body;

	public Message() {
	}

	public Message(String name, MessageType messageType, String body) {
		this.name = name;
		this.messageType = messageType;
		this.body = body;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String toString() {
		return name + ", " + messageType + ", " + body;
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = Maps.newHashMap();
		map.put("name", name);
		map.put("messageType", messageType.toString());
		map.put("body", body);
		return map;
	}
}
