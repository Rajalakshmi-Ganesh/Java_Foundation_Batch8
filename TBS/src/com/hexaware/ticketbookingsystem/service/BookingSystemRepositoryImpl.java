package com.hexaware.ticketbookingsystem.service;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.ticketbookingsystem.dao.BookingSystemServiceProviderImpl;
import com.hexaware.ticketbookingsystem.dao.EventServiceProviderImpl;
import com.hexaware.ticketbookingsystem.entity.Booking;
import com.hexaware.ticketbookingsystem.entity.Customer;
import com.hexaware.ticketbookingsystem.entity.Event;
import com.hexaware.ticketbookingsystem.entity.Venue;
import com.hexaware.ticketbookingsystem.exception.EventNotFoundException;
import com.hexaware.ticketbookingsystem.exception.InvalidBookingIDException;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository{
	
	BookingSystemServiceProviderImpl booking = new BookingSystemServiceProviderImpl();
	
	EventServiceProviderImpl event = new EventServiceProviderImpl();

	@Override
	public Event create_event(String event_name, String date, String time, int total_seats, double ticket_price,
			String event_type, Venue venue) {
		// TODO Auto-generated method stub
		try {
			
			return event.create_event(event_name, date, time, total_seats, ticket_price, event_type, venue);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Event> getEventDetails() {
		// TODO Auto-generated method stub
		try {
			
			return event.getEventDetails();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public int getAvailableNoOfTickets(int eventId) {
		// TODO Auto-generated method stub
		
		try {
			
			return event.getAvailableNoOfTickets(eventId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}

	@Override
	public double calculate_booking_cost(int num_tickets, int event_id) {
		// TODO Auto-generated method stub
		try {
			
			return booking.calculate_booking_cost(num_tickets,event_id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}

	@Override
	public Booking book_tickets(String eventname, int num_tickets, List<Customer> customers) throws EventNotFoundException {
		// TODO Auto-generated method stub
		try {
			
			return booking.book_tickets(eventname, num_tickets, customers);
			
		} catch (EventNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean cancel_booking(int booking_id) throws InvalidBookingIDException {
		// TODO Auto-generated method stub
		try {
			
			return booking.cancel_booking(booking_id);
			
		} catch (InvalidBookingIDException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public void get_booking_details(int booking_id) throws InvalidBookingIDException {
		// TODO Auto-generated method stub
		try {
			
			booking.get_booking_details(booking_id);
			
		} catch (InvalidBookingIDException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}

}
