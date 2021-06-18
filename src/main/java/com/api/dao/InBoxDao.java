package com.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.model.InBoxModel;

public interface InBoxDao extends CrudRepository<InBoxModel, Integer> {

	List<InBoxModel> findByReciever(String reciever);
	InBoxModel findById(int id);

}
