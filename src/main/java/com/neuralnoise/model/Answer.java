package com.neuralnoise.model;

import java.io.Serializable;

import com.neuralnoise.model.Request.RequestType;

public class Answer implements Serializable {

	private static final long serialVersionUID = -6145575227509614226L;
	
	private String name;
	private RequestType requestType;
	private String body;

	public Answer() { }

	public Answer(String name, RequestType requestType, String body) {
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
		return "Answer [name=" + name + ", requestType=" + requestType + ", body=" + body + "]";
	}

}
