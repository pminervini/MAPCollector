package com.neuralnoise.model;

public class ServiceStatus {
	
	public enum StatusType {
		OK, FAILURE
	}

	private StatusType status;

	private String version;

	public ServiceStatus(StatusType status) {
		this.status = status;
	}

	public ServiceStatus(StatusType status, String version) {
		this.status = status;
		this.version = version;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
