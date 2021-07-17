package com.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.model.BinModel;

public interface BinDao extends CrudRepository<BinModel, Integer> {

	List<BinModel> findByUsermail(String usermail);	
	BinModel findById(int id);


}
