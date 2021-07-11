package com.api.controller;

import java.time.LocalDateTime;

public class MailCastingErrorResponse {
	
	private int status;
	private String message;
	private  LocalDateTime dateAndTime;
	
	public MailCastingErrorResponse(int status, String message, LocalDateTime dateAndTime) {
		super();
		this.status = status;
		this.message = message;
		this.dateAndTime = dateAndTime;
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
	
	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	@Override
	public String toString() {
		return "MailCastingErrorResponse [status=" + status + ", message=" + message + ", dateAndTime=" + dateAndTime + "]";
	}


}
