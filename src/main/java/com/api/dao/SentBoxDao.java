package com.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.model.SentBoxModel;

public interface SentBoxDao extends CrudRepository<SentBoxModel, Integer> {

	List<SentBoxModel> findBySender(String sender);
	SentBoxModel findById(int id);

}
