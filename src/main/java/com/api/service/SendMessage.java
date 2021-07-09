package com.api.service;
import java.util.Calendar;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.InBoxDao;
import com.api.dao.SentBoxDao;
import com.api.dao.UserDao;
import com.api.model.InBoxModel;
import com.api.model.SentBoxModel;


@Service
@Transactional
public class SendMessage {
	 @Autowired
	 InBoxDao inboxdao;
	 @Autowired
	 SentBoxDao sentboxdao;
	 @Autowired
	 UserDao userdao;
	 
	 private Logger logger=Logger.getLogger(getClass().getName());
	 
	 public  boolean sendMsg(SentBoxModel mail){
		 
		 logger.info("\n"+mail.getSender() +"is Trying to send mail to "+mail.getReciever());
		 
		 if(userdao.findByEmail(mail.getReciever())==null) {
				logger.info("\n"+mail.getReciever()+" is not found");
				return false;
		 	}
			
		java.util.Date sqdate=Calendar.getInstance().getTime();
		java.sql.Timestamp sqlTime=new java.sql.Timestamp(sqdate.getTime());
		
		mail.setDate(sqlTime);
		
		InBoxModel inboxmail=new InBoxModel();
		
		inboxmail.setMessage(mail.getMessage());
		inboxmail.setReciever(mail.getReciever());
		inboxmail.setSender(mail.getSender());
		inboxmail.setSubject(mail.getSubject());
		inboxmail.setDate(sqlTime);
		
	
		inboxdao.save(inboxmail);
		logger.info("\nMail send to "+mail.getReciever()+" inbox");
		
		sentboxdao.save(mail);
		logger.info("\nMail saved to "+mail.getSender()+" sentbox");
		return true;
	}
}
