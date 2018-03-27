package com.rout.travels.itinerary.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.rout.travels.itinerary.model.SegmentT;

public interface SegmentTRepository extends Repository<SegmentT, Integer> {
	
	SegmentT save(SegmentT entity);

	List<SegmentT> findByConnRefIdAndCarrierCode(@Param(value="connRefId")Long connectionId, @Param(value="carrierCode")String carrierCode);

	List<SegmentT> findByConnRefIdAndOrigin(@Param(value="connRefId")Long connectionId, @Param(value="origin")String carrierCode);
	List<SegmentT> findByConnRefIdAndDestination(@Param(value="connRefId")Long connectionId, @Param(value="destination")String carrierCode);
	List<SegmentT> findByConnRefIdAndDepartureDate(@Param(value="connRefId")Long connectionId, @Param(value="departureDate")Date departureDate);
}
