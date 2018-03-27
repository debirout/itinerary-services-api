package com.rout.travels.itinerary.serviceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rout.travels.itinerary.dao.repository.ConnectionTRepository;
import com.rout.travels.itinerary.dao.repository.SegmentTRepository;
import com.rout.travels.itinerary.model.ConnectionT;
import com.rout.travels.itinerary.model.ItineraryT;
import com.rout.travels.itinerary.model.SegmentT;
import com.rout.travels.itinerary.service.ItineraryService;

@Service
public class ItineraryServiceImpl implements ItineraryService {
	
	@Autowired
	SegmentTRepository segmentRepo;
	@Autowired
	ConnectionTRepository connectionRepo;
	
 enum CRITERIA_CONSTANTS{
	CARRIERCODE(1),ORIGIN(2),DESTINATION(3),DEPARTUREDATE(4);
	 int val;
	 String criteriaKey;
	 CRITERIA_CONSTANTS(int val){
		 this.val=val;
	 }
	 static CRITERIA_CONSTANTS getCriteria(String key){
		 if("carrierCode".equals(key))
			 return CARRIERCODE;
		 else if("origin".equals(key))
			 return ORIGIN;
		 else if("destination".equals(key))
			 return DESTINATION;
		 else
			 return DEPARTUREDATE;
	 }
}

	
	public SegmentT createSegment(Long connectionId,SegmentT segment){
		segment.setConnRefId(connectionId);
		return segmentRepo.save(segment);
	}
	
	
	public ConnectionT createConnection(Long itineraryId,ConnectionT connection){
		connection.setItnrRefId(itineraryId);
		return connectionRepo.save(connection);
	}
	
	Collection<SegmentT> filterByCriteria(Long connId,String filterKey,String filterValue) {
		try {
			switch(CRITERIA_CONSTANTS.getCriteria(filterKey).val){
			case 1: return segmentRepo.findByConnRefIdAndCarrierCode(connId, filterValue);
					
			case 2: return segmentRepo.findByConnRefIdAndOrigin(connId, filterValue);
			        
			case 3: return segmentRepo.findByConnRefIdAndDestination(connId, filterValue);
			        
			default:return segmentRepo.findByConnRefIdAndDepartureDate(connId, new SimpleDateFormat("yyyy-MM-dd").parse(filterValue));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<SegmentT> fetchSegmentUnderConnection(Long connectionId, String filterKey, String filterValue) {
		// TODO Auto-generated method stub
		return filterByCriteria(connectionId,filterKey,filterValue);
	}
	

	@Override
	public Collection<SegmentT> fetchSegmentUnderItinerary(Long itineraryId,String filterKey,String filterValue) {
		// TODO Auto-generated method stub
		List<SegmentT> filteredSegments = new ArrayList<>();
		
			List<ConnectionT> connections = connectionRepo.findByItnrRefId(itineraryId);
			
			connections.forEach(connection->filteredSegments.addAll(fetchSegmentUnderConnection(connection.getConnId(),filterKey,filterValue)));
			
	
		return filteredSegments;
	}


	
	
	
	

	

}
