package com.hexaware.ticketbookingsystem.dao;

import java.util.List;

import com.hexaware.ticketbookingsystem.entity.Concert;
import com.hexaware.ticketbookingsystem.entity.Event;
import com.hexaware.ticketbookingsystem.entity.Movie;
import com.hexaware.ticketbookingsystem.entity.Sports;
import com.hexaware.ticketbookingsystem.entity.Venue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class EventServiceProviderImpl implements IEventServiceProvider{

	@Override
	public Event create_event(String event_name, String date, String time, int total_seats, double ticket_price,
			String event_type, Venue venue) {
		// TODO Auto-generated method stub
		Event event = null;
		
		 try (Connection conn = DBUtil.getDBConnection()){
			 
			 String query = "INSERT INTO event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) "+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			 
			 PreparedStatement ps = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			 
			 ps.setString(1, event_name);
	         ps.setDate(2, Date.valueOf(date));
	         ps.setTime(3, Time.valueOf(time));
	         ps.setInt(4, venue.getVenueId());
	         ps.setInt(5, total_seats);
	         ps.setInt(6, total_seats);  
	         ps.setDouble(7, ticket_price);
	         ps.setString(8, event_type);
	         
	         int count = ps.executeUpdate();
	         
	         if(count == 0) {
	        	 
	        	 System.out.println("insert failed.");
	        	 return null;
	        	 
	         }
	         
	         ResultSet rs = ps.getGeneratedKeys();
	         
	         int eventId=0;
	         
	         if(rs.next()) {
	        	 
	        	 eventId = rs.getInt(1);
	         }
	         
	         Scanner sc = new Scanner(System.in);
	         
	         switch (event_type.toLowerCase()) {
	         
	            case "movie":
	            	
	                System.out.println("Enter Genre:");
	                String genre = sc.nextLine();
	                
	                System.out.println("Enter Actor Name:");
	                String actor = sc.nextLine();

	                System.out.println("Enter Actress Name:");
	                String actress = sc.nextLine();

	                String movie_query = "INSERT INTO movie_event(event_id, genre, actor_name, actress_name) VALUES (?, ?, ?, ?)";
	                
	                PreparedStatement moviePS = conn.prepareStatement(movie_query);
	                
	                moviePS.setInt(1, eventId);
	                moviePS.setString(2, genre);
	                moviePS.setString(3, actor);
	                moviePS.setString(4, actress);
	                
	                moviePS.executeUpdate();

	                event = new Movie(eventId, event_name, Date.valueOf(date), Time.valueOf(time), venue.getVenueName(),total_seats, total_seats, ticket_price, "Movie", genre, actor, actress);
	                return event;
	                
	            case "concert":
	            	
	                System.out.println("Enter Artist Name:");
	                String artist = sc.nextLine();

	                System.out.println("Enter Concert Type:");
	                String concertType = sc.nextLine();

	                String concert_query = "INSERT INTO concert_event(event_id, artist_name, concert_type) VALUES (?, ?, ?)";
	                
	                PreparedStatement concertPS = conn.prepareStatement(concert_query);
	                
	                concertPS.setInt(1, eventId);
	                concertPS.setString(2, artist);
	                concertPS.setString(3, concertType);
	                concertPS.executeUpdate();

	                event = new Concert(eventId, event_name, Date.valueOf(date), Time.valueOf(time), venue.getVenueName(),total_seats, total_seats, ticket_price, "Concert", artist, concertType);
	                return event;
	                
	            case "sports":
	            	
	                System.out.println("Enter Sport Name:");
	                String sport = sc.nextLine();

	                System.out.println("Enter Team Names:");
	                String teams = sc.nextLine();

	                String sports_query = "INSERT INTO sports_event(event_id, sport_name, teams) VALUES (?, ?, ?)";
	                
	                PreparedStatement sportsPS = conn.prepareStatement(sports_query);
	                
	                sportsPS.setInt(1, eventId);
	                sportsPS.setString(2, sport);
	                sportsPS.setString(3, teams);
	                sportsPS.executeUpdate();

	                event = new Sports(eventId, event_name, Date.valueOf(date), Time.valueOf(time), venue.getVenueName(),total_seats, total_seats, ticket_price, "Sports", sport, teams);
	                return event; 
	                
	            default:
	                System.out.println("Invalid event type.");
	                return null;
	        }
	         
			 
		 }catch (SQLException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		return null;
	}
	
	private String getVenueNameById(int venueId) {
		
	    try (Connection conn = DBUtil.getDBConnection()) {
	    	
	        String query = "SELECT venue_name FROM venue WHERE venue_id = ?";
	        
	        PreparedStatement ps = conn.prepareStatement(query);
	        
	        ps.setInt(1, venueId);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	        	
	            return rs.getString("venue_name");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "id not found";
	}

	@Override
	public List<Event> getEventDetails() {
		// TODO Auto-generated method stub
		List<Event> events = new ArrayList<Event>();
		
		try (Connection conn = DBUtil.getDBConnection()){
					
			String query = "SELECT e.*, v.venue_name, v.address FROM event e JOIN venue v ON e.venue_id = v.venue_id";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int eventId = rs.getInt("event_id");
	            String name = rs.getString("event_name");
	            Date date = rs.getDate("event_date");
	            Time time = rs.getTime("event_time");
	            String venueName = getVenueNameById(rs.getInt("venue_id"));
	            double ticketPrice = rs.getDouble("ticket_price");
	            int totalSeats = rs.getInt("total_seats");
	            int availableSeats = rs.getInt("available_seats");
	            String eventType = rs.getString("event_type");
	            
	            Event event = null;
	            
	            switch(eventType.toLowerCase()) {
	            
	            case "movie":
	            	
	            	String movieSql = "SELECT genre, actor_name, actress_name FROM movie_event WHERE event_id = ?";
                    PreparedStatement movieStmt = conn.prepareStatement(movieSql);
                    movieStmt.setInt(1, eventId);
                    ResultSet movieRs = movieStmt.executeQuery();
                    if (movieRs.next()) {
                        String genre = movieRs.getString("genre");
                        String actor = movieRs.getString("actor_name");
                        String actress = movieRs.getString("actress_name");
                        event = new Movie(eventId, name, date, time, venueName, totalSeats, availableSeats, ticketPrice, eventType, genre, actor, actress);
                    }
                    movieRs.close();
                    movieStmt.close();
                    break;

                case "concert":
                	String concertSql = "SELECT artist_name, concert_type FROM concert_event WHERE event_id = ?";
                    PreparedStatement concertStmt = conn.prepareStatement(concertSql);
                    concertStmt.setInt(1, eventId);
                    ResultSet concertRs = concertStmt.executeQuery();
                    if (concertRs.next()) {
                        String artist = concertRs.getString("artist_name");
                        String concertType = concertRs.getString("concert_type");
                        event = new Concert(eventId, name, date, time, venueName, totalSeats, availableSeats, ticketPrice, eventType, artist, concertType);
                    }
                    break;

                case "sports":
                	
                	String sportsSql = "SELECT sport_name, teams FROM sports_event WHERE event_id = ?";
                    PreparedStatement sportsStmt = conn.prepareStatement(sportsSql);
                    sportsStmt.setInt(1, eventId);
                    ResultSet sportsRs = sportsStmt.executeQuery();
                    if (sportsRs.next()) {
                        String sport = sportsRs.getString("sport_name");
                        String teams = sportsRs.getString("teams");
                        event = new Sports(eventId, name, date, time, venueName, totalSeats, availableSeats, ticketPrice, eventType, sport, teams);
                    }
                    sportsRs.close();
                    sportsStmt.close();
                    break;

                default:
                    System.out.println("Invalid event type.");
                    
                    return null;
	            }
	            
	            if(event!=null) {
	            	
	            	events.add(event);
	            }
			}
					
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return events;
	}

	@Override
	public int getAvailableNoOfTickets(int eventId) {
		// TODO Auto-generated method stub
		int availableTickets = 0;
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "SELECT available_seats FROM event WHERE event_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, eventId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				availableTickets = rs.getInt("available_seats");
			}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return availableTickets;
	}

}
