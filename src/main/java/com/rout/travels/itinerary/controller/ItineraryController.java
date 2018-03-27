package com.rout.travels.itinerary.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rout.travels.itinerary.service.ItineraryService;
import com.rout.travels.itinerary.model.*;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
	
	private Logger logger = Logger.getLogger(ItineraryController.class);
	
	@Autowired
	ItineraryService itineraryService;
	
	@RequestMapping(value="{itineraryId}/connections",method=RequestMethod.POST,produces="application/json")
	ResponseEntity<ConnectionT> createConnection(@PathVariable Long itineraryId,@RequestBody ConnectionT connection){
		connection = itineraryService.createConnection(itineraryId,connection);
		logger.info(":::Connection Id post DBpersistence:::"+connection.getConnId());
		URI uriOfNewItinerary = ServletUriComponentsBuilder.fromCurrentContextPath().path("/itineraries/{itineraryId}/connections/{connectionId}")
				.buildAndExpand(itineraryId,connection.getConnId())
				.toUri();
		HttpHeaders header =new HttpHeaders(); 
		header.setLocation(uriOfNewItinerary);
		
		
		return new ResponseEntity<>(connection,header,HttpStatus.CREATED);
		
		
	}
	
	@RequestMapping(value="{itineraryId}/connections/{connectionId}/segments",method=RequestMethod.POST , produces="application/json")
	ResponseEntity<SegmentT> createSegment(@PathVariable Long itineraryId, @PathVariable Long connectionId,@RequestBody SegmentT segment){
		segment=itineraryService.createSegment(connectionId, segment);
		
		URI uriOfNewSegment = ServletUriComponentsBuilder.fromCurrentContextPath().path("/itineraries/{itineraryId}/connections/{connectionId}/segments/{segmentId}")
				.buildAndExpand(itineraryId,connectionId,segment.getSegId())
				.toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uriOfNewSegment);
		return new ResponseEntity<>(segment,header,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="{itineraryId}/connections",method=RequestMethod.GET,produces="application/json")
	Collection<SegmentT> loadSegmentUnderItinerary(@PathVariable(value="itineraryId") Long itId,
			@RequestParam(value="filterKey",required=true) String filterKey,
			@RequestParam(value="filterValue",required=true)String filterValue){
		
		return itineraryService.fetchSegmentUnderItinerary(itId, filterKey,filterValue);
		
		
	}
	
	
	@RequestMapping(value="{itineraryId}/connections/{connectionId}/segments",method=RequestMethod.GET,produces="application/json")
	Collection<SegmentT> loadSegmentUnderConnection(@PathVariable(value="itineraryId") Long itId,
			@PathVariable(value="connectionId") Long conId,
			@RequestParam(value="filterKey",required=true) String filterKey,
			@RequestParam(value="filterValue",required=true)String filterValue){
		
		return itineraryService.fetchSegmentUnderConnection( conId, filterKey,filterValue);
		
		
	}
	
	

}
