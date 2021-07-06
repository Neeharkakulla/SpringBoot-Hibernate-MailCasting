package com.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.api.dao.UserDao;
import com.api.model.UserModel;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserDao userdao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user=userdao.findByEmail(username);
		UserDetailsImpl userDetails=null;
		if(user!=null) {
			userDetails=new UserDetailsImpl();
			userDetails.setUser(user);
		}
		else {
			throw new UsernameNotFoundException(username+" user doesn't not exists");
		}
		return userDetails;

	}

}
