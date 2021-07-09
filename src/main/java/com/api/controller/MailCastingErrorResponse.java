package com.api.controller;

import java.sql.Timestamp;

public class MailCastingErrorResponse {
	
	private int status;
	private String message;
	private Timestamp timeStamp;
	
	public MailCastingErrorResponse(int status, String message, Timestamp timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "MailCastingErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}


}
