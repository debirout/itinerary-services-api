package com.rout.travels.itinerary.service;

import java.util.Collection;

import com.rout.travels.itinerary.model.ConnectionT;
import com.rout.travels.itinerary.model.ItineraryT;
import com.rout.travels.itinerary.model.SegmentT;

public interface ItineraryService {
	
	public ConnectionT createConnection(Long itineraryId,ConnectionT connection);
	public SegmentT createSegment(Long connectionId,SegmentT segment);
	public Collection<SegmentT> fetchSegmentUnderItinerary(Long itineraryId,String filterKey,String filterValue);
	public Collection<SegmentT> fetchSegmentUnderConnection(Long connectionId,String filterKey,String filterValue);

}
