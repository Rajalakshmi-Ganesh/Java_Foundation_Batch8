package com.hexaware.ticketbookingsystem.presentation;

import com.hexaware.ticketbookingsystem.entity.*;

import com.hexaware.ticketbookingsystem.exception.EventNotFoundException;
import com.hexaware.ticketbookingsystem.exception.InvalidBookingIDException;
import com.hexaware.ticketbookingsystem.service.BookingSystemRepositoryImpl;
import com.hexaware.ticketbookingsystem.service.EventComparator;
import com.hexaware.ticketbookingsystem.service.IBookingSystemRepository;


import java.util.*;

public class TicketBookingSystem {

		public static void main(String args[]) {
			
			Scanner sc = new Scanner(System.in); 
			
			IBookingSystemRepository tbs = new BookingSystemRepositoryImpl();
			
			Set<Booking> bookingSet = new HashSet<Booking>();
			
			Map<Integer, Booking> bookingMap = new HashMap<Integer,Booking>();
			
			boolean flag = true;
			
			while (flag) {
				
			
	            System.out.println("Ticket Booking System ");
	            System.out.println("1. View Events");
	            System.out.println("2. Create New Event");
	            System.out.println("3. Book Tickets");
	            System.out.println("4. Cancel Booking");
	            System.out.println("5. View Booking Details");
	            System.out.println("0. Exit");
	            System.out.print("Choose an option: ");
	            
	            int choice = sc.nextInt();
	            sc.nextLine();
	            
	            switch (choice) {
	            
	            	case 1:
	            		
	            		List<Event> events = tbs.getEventDetails();
	            		
		                if (events != null && !events.isEmpty()) {
		                	
		                	
		                	Collections.sort(events, new EventComparator());
		                
		                    for (Event e : events) {
		                    	
		                    
		                        System.out.println(e);
		                        System.out.println("\n");
		                    }
		                } 
		                else {
		                	
		                    System.out.println("No events found.");
		                }
		                break;
	                
	            	case 2:
	            		
	        			System.out.print("Event Name: ");
	                    String name = sc.nextLine();
	                    
	                    System.out.print("Event Date (YYYY-MM-DD): ");
	                    String date = sc.nextLine();

	                    System.out.print("Event Time (HH:MM:SS): ");
	                    String time = sc.nextLine();

	                    System.out.print("Total Seats: ");
	                    int totalSeats = sc.nextInt();

	                    System.out.print("Ticket Price: ");
	                    double price = sc.nextDouble();
	                    sc.nextLine(); 

	                    System.out.print("Event Type (movie/concert/sports): ");
	                   
	                    String type = sc.nextLine();

	                   
	                    Venue venue = new Venue(1, "Hexa Arena", "New Zee");
	                    
	                    Event event = tbs.create_event(name, date, time, totalSeats, price, type, venue);

	                    if (event != null) {
	                        System.out.println("Event created: " + event.getEventName());
	                    }
	                    
	                    break;
	            		
	            	case 3:
	            		
		      			try {
		      			  
		  		          System.out.print("Enter event name: ");
		  		          String eventName = sc.nextLine();
	
		                  System.out.print("Enter number of tickets: ");
		  	              int numTickets = Integer.parseInt(sc.nextLine());
	
	  		              List<Customer> customerList = new ArrayList<Customer>();
	
	  		              for (int i = 1; i <= numTickets; i++) {
	  		            	
	  		                  System.out.println("Enter details for Customer " + i);
	  		                  System.out.print("Name: ");
	  		                  String custName = sc.nextLine();
	  		                
	  		                  System.out.print("Email: ");
	  		                  String email = sc.nextLine();
	  		                
	  		                  System.out.print("Phone Number: ");
	  		                  String phone = sc.nextLine();

	  		                  customerList.add(new Customer(custName, email, phone));
	  		              }
	
		  		          Booking booking = tbs.book_tickets(eventName, numTickets, customerList);
		  		            
		  		          if (booking != null) {
		  		            	
		  		              System.out.println("Tickets booked successfully.");
		  		              
		  		            bookingSet.add(booking);
		  		            bookingMap.put(booking.getBookingId(), booking);
		  		            
		  		          } 
		  		          else {
		  		            	
		  		              System.out.println("Ticket booking failed.");
		  		          }
	
		  		        } catch (EventNotFoundException e) {
		  		        	
		  		            System.out.println("Error: " + e.getMessage());
		  		            
		  		        } catch (Exception e) {
		  		        	
		  		            System.out.println("An unexpected error occurred:");
		  		            e.printStackTrace();
		  		        } 
		      			  
		      	    	break;
	      			
	            	case 4:
	            		
	            		try {
	            			
	            			System.out.println("Enter booking id: ");
	            			int bookingId = sc.nextInt();
	            			
	        			    boolean isCancelled = tbs.cancel_booking(bookingId);
	        			    
	        			    if (isCancelled) {
	        			    	
	        			        System.out.println("Booking cancelled successfully.");
	        			        
	        			    } else {
	        			    	
	        			        System.out.println("Booking cancellation failed.");
	        			    }
	        			} catch (InvalidBookingIDException e) {
	        				
	        			    System.out.println("Error: " + e.getMessage());
	        			    
	        			} catch (Exception e) {
	        				
	        			    e.printStackTrace();
	        			}
	            		
	            		break;
	            		
	            	case 5:
	            		
	            		try {
	            			System.out.println("Enter booking id: ");
	            			int id = sc.nextInt();
	            			
	        				tbs.get_booking_details(id);
	        				
	        			} catch (InvalidBookingIDException e) {
	        				
	        			    System.out.println("Error: " + e.getMessage());
	        			    
	        			} catch (Exception e) {
	        				
	        			    e.printStackTrace();
	        			}
	            		break;
	            		
	            	case 0:
	                	
	                    flag = false;
	                    
	                    System.out.println("Thanks! visit again...");
	                    break;
	                    
	                default:
	                	
	                    System.out.println("Invalid choice.");
	            		
	            }
			}
	            		
	            		
			for (Booking b : bookingSet) {
				
			    System.out.println(b);
			    
			} 
	            		
			int idToSearch = sc.nextInt();
			
			if (bookingMap.containsKey(idToSearch)) {
				
			    System.out.println("Booking Found: " + bookingMap.get(idToSearch));
			    
			} 
			else {
				
			    System.out.println("No booking with that ID.");
			}   		
	            		
	            
	      
			

			
			
			
//			int ant = tbs.getAvailableNoOfTickets(10);
//			System.out.println(ant);
			
			
//			double res = tbs.calculate_booking_cost(5, 2);
//			System.out.println(res);
			
			  
			
			
			
	            }
}
