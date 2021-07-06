package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.SentBoxDao;
import com.api.model.SentBoxModel;


@Service
@Transactional
public class SentBoxService {
	@Autowired
	SentBoxDao sentdao;
	
	private Logger logger=Logger.getLogger(getClass().getName());

	public  void deleteById(int id) {
		logger.info("\nSentBoxMail adding to bin with id "+id);
		sentdao.deleteById(id);
		}
	
	public  List<SentBoxModel> getAllMailsByEmail(String sender){
		List<SentBoxModel> list=sentdao.findBySender(sender);
			
		if(list.size()>0)
			return list.stream().
					sorted((m1,m2)->-(m1.getDate()).compareTo((m2.getDate())))
					.collect(Collectors.toCollection(ArrayList::new));
		logger.info("\nGetting Sentbox Mails of "+sender);
		return list;
	}
	
	public  SentBoxModel getMailById(int id) {
		SentBoxModel mail=sentdao.findById(id);
		logger.info("\nGetting Sentbox Emails by id : "+id);
		
		return mail;
		}

	public  void retriveMail(SentBoxModel mail) {
		logger.info("\nRetriving mail to sentbox from bin");
		sentdao.save(mail);
	}


}
