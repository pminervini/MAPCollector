package com.neuralnoise.model;

import java.io.Serializable;

public class Request implements Serializable {
	
	private static final long serialVersionUID = -3318322156595107926L;

	public enum RequestType {
		CREATE, UPDATE, DELETE
	}

	private String name;
	private RequestType requestType;
	private String body;

	public Request() { }

	public Request(String name, RequestType requestType, String body) {
		this.name = name;
		this.requestType = requestType;
		this.body = body;
	}

	public String getName() {
		return name;
	}
	
	public RequestType getRequestType() {
		return requestType;
	}
	
	public String getBody() {
		return body;
	}
	
	@Override
	public String toString() {
		return "Request [name=" + name + ", requestType=" + requestType + ", body=" + body + "]";
	}

}
