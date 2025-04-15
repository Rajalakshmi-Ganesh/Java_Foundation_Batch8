package com.hexaware.ticketbookingsystem.dao;

import com.hexaware.ticketbookingsystem.entity.Event;
import com.hexaware.ticketbookingsystem.entity.Venue;

import java.util.*;

public interface IEventServiceProvider {
	
	/**
	 * 
	 * @param event_name
	 * @param date
	 * @param time
	 * @param total_seats
	 * @param ticket_price
	 * @param event_type
	 * @param venue
	 * @return
	 */
	Event create_event(String event_name, String date, String time, int total_seats, double ticket_price, String event_type, Venue venue);
	
	/**
	 * 
	 * @return
	 */
    List<Event> getEventDetails();
    
    
    /**
     * 
     * @param eventId
     * @return
     */
    int getAvailableNoOfTickets(int eventId);

}
