package com.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.model.UserModel;
import com.api.service.UserService;

public class UserServiceTesting extends Batch10MailCastingSpringBootApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	public void validateUserLogin() throws Exception {
		assertEquals(true,userService.checkLogin("neehar@gmail.com", "neehar")) ;
	}
	@Test
	public void validateFakeUserLogin() throws Exception {
		
		assertEquals(false,userService.checkLogin("neehar@gmail.com", "neehar1")) ;
	}
	
	@Test
	public void validatePassword() throws Exception{
		assertEquals(true, userService.validatePassword(1,"neehar"));
	}

	@Test
	public void validateFakePassword() throws Exception{
		assertEquals(false, userService.validatePassword(1,"wrongpassword"));
	}

	@Test
	public void getUser() throws Exception{
		String email="neehar@gmail.com";
		
		UserModel dbuser=userService.getUserByEmail(email);
		assertEquals(true, dbuser!=null);
		
		
	}
	
	@Test
	public void getFakeUser() throws Exception{
		
		String fakeEmail="123@gmail.com";
		
		UserModel dbuser=userService.getUserByEmail(fakeEmail);
		assertEquals(true, dbuser==null);
		
	}
	
}