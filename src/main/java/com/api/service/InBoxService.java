package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.InBoxDao;
import com.api.model.InBoxModel;


@Service
@Transactional
public class InBoxService {
	
	@Autowired
	InBoxDao inboxdao;
	
	private Logger logger=Logger.getLogger(getClass().getName());

	public  void deleteById(int id) {
		logger.info("\nInboxMail adding to bin with id "+id);
		inboxdao.deleteById(id);
	}
	
	public  List<InBoxModel> getAllMailsByEmail(String reciever){
		List<InBoxModel> list=inboxdao.findByReciever(reciever);
		
		if(list.size()>0)
			return list.stream().
					sorted((m1,m2)->-(m1.getDate()).compareTo((m2.getDate())))
					.collect(Collectors.toCollection(ArrayList::new));
		
		logger.info("\nGetting Inbox Mails of "+reciever);
		
		return list;
	}
	public  InBoxModel getMailById(int id) {
		InBoxModel mail=inboxdao.findById(id);
		
		logger.info("\nGetting Inbox Emails by id : "+id);
		
		return mail;
		
	}
	public  void retriveMail(InBoxModel mail) {
		
		logger.info("\nRetriving mail to inbox from bin");
		
			inboxdao.save(mail);
	}

}
