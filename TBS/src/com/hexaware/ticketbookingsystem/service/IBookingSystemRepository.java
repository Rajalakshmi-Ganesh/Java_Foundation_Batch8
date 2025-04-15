package com.hexaware.ticketbookingsystem.service;

import com.hexaware.ticketbookingsystem.entity.*;
import com.hexaware.ticketbookingsystem.exception.EventNotFoundException;
import com.hexaware.ticketbookingsystem.exception.InvalidBookingIDException;

import java.util.*;

public interface IBookingSystemRepository {
	
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

    /**
     * 
     * @param num_tickets
     * @param ticketPrice
     * @return
     */
    double calculate_booking_cost(int num_tickets,int event_id);
    
    /**
     * 
     * @param eventname
     * @param num_tickets
     * @param customers
     * @return
     * @throws Exception
     */
    Booking book_tickets(String eventname, int num_tickets, List<Customer> customers) throws EventNotFoundException;
    
    /**
     * 
     * @param booking_id
     * @return
     * @throws Exception
     */
    boolean cancel_booking(int booking_id) throws InvalidBookingIDException;
    
    /**
     * 
     * @param booking_id
     * @return
     * @throws Exception
     */
    void get_booking_details(int booking_id) throws InvalidBookingIDException;
}
