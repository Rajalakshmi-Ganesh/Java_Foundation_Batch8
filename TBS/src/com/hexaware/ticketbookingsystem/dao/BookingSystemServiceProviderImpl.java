package com.hexaware.ticketbookingsystem.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.ticketbookingsystem.entity.Booking;
import com.hexaware.ticketbookingsystem.entity.Customer;
import com.hexaware.ticketbookingsystem.exception.EventNotFoundException;
import com.hexaware.ticketbookingsystem.exception.InvalidBookingIDException;


public class BookingSystemServiceProviderImpl implements IBookingSystemServiceProvider{

	@Override
	public double calculate_booking_cost(int num_tickets, int event_id) {
		// TODO Auto-generated method stub
		double ticketPrice = 0.0;
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "SELECT ticket_price FROM event where event_id =?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, event_id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
		        ticketPrice = rs.getDouble("ticket_price");
		    }
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return num_tickets*ticketPrice;
	}

	@Override
	public Booking book_tickets(String eventName, int num_tickets, List<Customer> customers) throws EventNotFoundException {
		// TODO Auto-generated method stub
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "SELECT * FROM event WHERE event_name = ?";
			
		    PreparedStatement ps = conn.prepareStatement(query);
		    
		    ps.setString(1, eventName);
		    
		    ResultSet rs = ps.executeQuery();
		    
		    if(!rs.next()) {
		    	
		    	throw new EventNotFoundException("Event not found");
		    	
		    }
		    
		    int eventId = rs.getInt("event_id");
		    int availableSeats = rs.getInt("available_seats");
		    double ticketPrice = rs.getDouble("ticket_price");
		    
		    if (availableSeats < num_tickets) {
		    	
		        System.out.println("Not enough seats available.");
		        return null;
		    }
		    
		    
		    List<Integer> customerIds = new ArrayList<Integer>();
		    
		    String find_customer = "SELECT customer_id FROM customer WHERE email = ?";
		    String customer_query = "INSERT INTO customer(customer_name,email,phone_number) VALUES (?, ?, ?)";
		    
		    for (Customer c : customers) {
		    	
		    	PreparedStatement findPS = conn.prepareStatement(find_customer);
	            findPS.setString(1, c.getEmail());
	            ResultSet custRS = findPS.executeQuery();
	            
	            
	            if (custRS.next()) {
	                
	                customerIds.add(custRS.getInt("customer_id"));
	                
	            } else {
		    	
			        PreparedStatement custPS = conn.prepareStatement(customer_query,Statement.RETURN_GENERATED_KEYS);
			        
			        custPS.setString(1, c.getCustomerName());
			        custPS.setString(2, c.getEmail());
			        custPS.setString(3, c.getPhoneNumber());
			        
			        custPS.executeUpdate();
	
			        ResultSet generatedKeys = custPS.getGeneratedKeys();
			        
			        if (generatedKeys.next()) {
			        	
			            customerIds.add(generatedKeys.getInt(1));
			        }
	            }
		    }
		    
	        if (customerIds.isEmpty()) {
	        	
	            System.out.println("No customers were saved.");
	            return null;
	        }
		    
	        int primaryCustomerId = customerIds.get(0);
	        
		    double total_cost = ticketPrice * num_tickets;
		    
		    LocalDate bookingDate = LocalDate.now();
		    
		    String insert_query = "INSERT INTO booking (customer_id, event_id, num_tickets, total_cost, booking_date) VALUES (?, ?, ?, ?, ?)";
		    
		    
		    	
	        PreparedStatement bookPS = conn.prepareStatement(insert_query,Statement.RETURN_GENERATED_KEYS);
	        
	        
	        bookPS.setInt(1, primaryCustomerId); 
	        bookPS.setInt(2, eventId);            
	        bookPS.setInt(3, num_tickets);        
	        bookPS.setDouble(4, total_cost);      
	        bookPS.setDate(5, Date.valueOf(bookingDate));
	        
	        int rows = bookPS.executeUpdate();
	        
	       
	        int bookingId = 0;
	        
	        if (rows > 0) {
	        	
	            ResultSet generatedKeys = bookPS.getGeneratedKeys();
	            
	            if (generatedKeys.next()) {
	            	
	                bookingId = generatedKeys.getInt(1);
	            }
	        }
	        
	        Booking booking = new Booking();
	        booking.setBookingId(bookingId);
	        booking.setCustomerId(primaryCustomerId);
	        booking.setEventId(eventId);
	        booking.setNumTickets(num_tickets);
	        booking.setTotalCost(total_cost);
	        booking.setBookingDate(Date.valueOf(bookingDate));
	       
		    
		    
		    String update_query = "UPDATE event SET available_seats = available_seats - ? WHERE event_id = ?";
		    
		    PreparedStatement updatePS = conn.prepareStatement(update_query);
		    
		    updatePS.setInt(1, num_tickets);
		    updatePS.setInt(2, eventId);
		    
		    updatePS.executeUpdate();
		    
		    System.out.println("Booking successful with total cost of "+total_cost);
		    return booking;
		    
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean cancel_booking(int booking_id) throws InvalidBookingIDException {
		// TODO Auto-generated method stub
		try (Connection conn = DBUtil.getDBConnection()){
			
			String check = "SELECT * FROM booking WHERE booking_id =?"; 
			
			PreparedStatement ps = conn.prepareStatement(check);
			
			ps.setInt(1, booking_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) {
				
				throw new InvalidBookingIDException("Booking not found.");
		        
			}
			
			int eventId = rs.getInt("event_id");
		    int numTickets = rs.getInt("num_tickets");
		    
		    String delete_query = "DELETE FROM booking WHERE booking_id = ?";
		    
		    PreparedStatement deletePS = conn.prepareStatement(delete_query);
		    
		    deletePS.setInt(1, booking_id);
		    
		    deletePS.executeUpdate();
		    
		    String update_query = "UPDATE event SET available_seats = available_seats + ? WHERE event_id = ?";
		    
		    PreparedStatement updatePS = conn.prepareStatement(update_query);
		    
		    updatePS.setInt(1, numTickets);
		    updatePS.setInt(2, eventId);
		    
		    updatePS.executeUpdate();
		    
		    
		    return true;
		    
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public void get_booking_details(int booking_id) throws InvalidBookingIDException {
		// TODO Auto-generated method stub
		
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "SELECT b.booking_id, b.num_tickets, b.total_cost, b.booking_date, e.event_id, e.event_name, v.venue_name,c.customer_id, c.customer_name AS customer_name, c.email FROM booking b JOIN event e ON b.event_id = e.event_id JOIN venue v ON e.venue_id = v.venue_id JOIN customer c ON b.customer_id = c.customer_id  WHERE b.booking_id = ?";
		
			PreparedStatement ps = conn.prepareStatement(query);
			
		    ps.setInt(1, booking_id);
		    
		    ResultSet rs = ps.executeQuery();
		    
		    if (rs.next()) {
		    	
	            System.out.println("Booking ID: " + rs.getInt("booking_id"));
	            System.out.println("Customer: " + rs.getString("customer_name") + " (Email: " + rs.getString("email") + ")");
	            System.out.println("Event: " + rs.getString("event_name"));
	            System.out.println("Venue: " + rs.getString("venue_name"));
	            System.out.println("Tickets: " + rs.getInt("num_tickets"));
	            System.out.println("Total Cost: ₹" + rs.getDouble("total_cost"));
	            System.out.println("Booking Date: " + rs.getDate("booking_date"));
	            
	        } 
		    else {
		    	
		    	throw new InvalidBookingIDException("Booking not found.");
	        }
		    
		    
		    
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
