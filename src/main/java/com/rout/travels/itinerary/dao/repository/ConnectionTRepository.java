package com.rout.travels.itinerary.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.rout.travels.itinerary.model.ConnectionT;


public interface ConnectionTRepository extends CrudRepository<ConnectionT, Integer> {

	List<ConnectionT> findByItnrRefId(@Param(value="itnrRefId")Long itineraryId);
	
	//ConnectionT save(ConnectionT entity);

	

}
