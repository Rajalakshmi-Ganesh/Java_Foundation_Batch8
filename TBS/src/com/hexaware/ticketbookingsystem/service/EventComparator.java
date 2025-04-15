package com.hexaware.ticketbookingsystem.service;

import java.util.Comparator;

import com.hexaware.ticketbookingsystem.entity.Event;

public class EventComparator implements Comparator<Event>{

	@Override
	public int compare(Event event1, Event event2) {
		// TODO Auto-generated method stub
		int nameComparison = event1.getEventName().toLowerCase().compareTo(event2.getEventName().toLowerCase());
	        
	        
        if (nameComparison == 0) {
        	
            return event1.getVenueName().compareTo(event2.getVenueName());
        }
        
        
        return nameComparison;
	}

}
