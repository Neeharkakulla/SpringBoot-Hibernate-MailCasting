package com.api.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dao.UserDao;
import com.api.model.UserModel;


@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userdao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	private Logger logger=Logger.getLogger(getClass().getName());

	
	public  boolean register(UserModel user){
		if(getUserByEmail(user.getEmail())==null) {	
			logger.info("\nUser is sucessfully saved with mail "+user.getEmail());
	 userdao.save(user);
	 return true;
		}
		logger.info("\nUser Already Exists in the database with mail "+user.getEmail());
		return false;
	}
	
	public  UserModel getUserByEmail(String email) {
		logger.info("\nGetting user by mail :"+email);
		return userdao.findByEmail(email);
	}
	
	public  boolean checkLogin(String email,String password){
		
		UserModel dbuser=userdao.findByEmail(email);
		
		
		
		if(dbuser!=null&&passwordEncoder.matches(password, dbuser.getPassword()))
			return true;
		
		return false;
		
	}
	
	public  boolean validatePassword(int id, String password) {
		UserModel dbuser=userdao.findById(id);
		
		if(passwordEncoder.matches(password, dbuser.getPassword())) {
			logger.info("\nPassword validation is sucess");
			return true;
		}
		logger.info("\nPassword validation is failed");
		return false;
	
	}
	
	public  boolean changePassword(int id, String password) {
		UserModel dbuser=userdao.findById(id);
		dbuser.setPassword(passwordEncoder.encode(password));
		userdao.save(dbuser);
		logger.info("\nPassword Change Request is accepted");
		return true;
	}
}
