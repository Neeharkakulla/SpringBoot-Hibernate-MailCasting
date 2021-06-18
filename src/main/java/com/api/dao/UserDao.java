package com.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.model.UserModel;

public interface UserDao extends CrudRepository<UserModel, Integer>{

	UserModel findByEmail(String email);
	UserModel findById(int id);

}
