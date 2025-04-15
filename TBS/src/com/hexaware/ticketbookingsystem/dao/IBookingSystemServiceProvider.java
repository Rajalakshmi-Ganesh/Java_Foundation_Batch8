package com.hexaware.ticketbookingsystem.dao;

import com.hexaware.ticketbookingsystem.entity.*;
import com.hexaware.ticketbookingsystem.exception.EventNotFoundException;
import com.hexaware.ticketbookingsystem.exception.InvalidBookingIDException;

import java.util.*;


public interface IBookingSystemServiceProvider {
	
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
    Booking book_tickets(String eventName, int num_tickets, List<Customer> customers) throws EventNotFoundException;
    
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
