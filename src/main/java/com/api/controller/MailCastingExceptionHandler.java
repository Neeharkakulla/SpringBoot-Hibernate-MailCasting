package com.api.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.jasper.JasperException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javassist.NotFoundException;

@ControllerAdvice
public class MailCastingExceptionHandler {
	
	
	
		
	@ExceptionHandler
	public ModelAndView handleException(NotFoundException e){
		
		MailCastingErrorResponse error=new MailCastingErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), new Timestamp(Calendar.getInstance().getTime().getTime())); 

		return new ModelAndView("error","error",error);
			
	}
	
	@ExceptionHandler
	public ModelAndView handleException(NumberFormatException e){
		
		MailCastingErrorResponse error=new MailCastingErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Exception", new Timestamp(Calendar.getInstance().getTime().getTime())); 
		
		return new ModelAndView("error","error",error);
			
	}
	
	@ExceptionHandler
	public ModelAndView handleException(ClassCastException e){
		
		
		
		MailCastingErrorResponse error=new MailCastingErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Exception", new Timestamp(Calendar.getInstance().getTime().getTime())); 
		
		return new ModelAndView("error","error",error);
		
	}
	
	@ExceptionHandler
	public ModelAndView handleException(NullPointerException e){
		
		MailCastingErrorResponse error=new MailCastingErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Exception", new Timestamp(Calendar.getInstance().getTime().getTime())); 
		
		return new ModelAndView("error","error",error);
		
	}
	
	@ExceptionHandler
	public ModelAndView handleException(JasperException e){
		
		MailCastingErrorResponse error=new MailCastingErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Exception", new Timestamp(Calendar.getInstance().getTime().getTime())); 
		
		return new ModelAndView("error","error",error);
				
	}
	
	@ExceptionHandler
	public ModelAndView handleException(Exception e){
		
		MailCastingErrorResponse error=new MailCastingErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Exception", new Timestamp(Calendar.getInstance().getTime().getTime())); 
		
		return new ModelAndView("error","error",error);
			
	}
	
}
