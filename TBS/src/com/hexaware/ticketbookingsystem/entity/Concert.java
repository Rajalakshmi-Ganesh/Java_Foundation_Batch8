package com.hexaware.ticketbookingsystem.entity;

import java.sql.Date;
import java.sql.Time;


public class Concert extends Event{
	
	 private String artist; 
     private String type;
     
     
	public Concert() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	



	public Concert(int eventId, String eventName, Date eventDate, Time eventTime, String venueName, int totalSeats,
			int availableSeats, double ticketPrice, String eventType, String artist, String type) {
		super(eventId, eventName, eventDate, eventTime, venueName, totalSeats, availableSeats, ticketPrice, "Concert");
		// TODO Auto-generated constructor stub
		this.artist = artist;
        this.type = type;
	}


	public Concert(int eventId, String name, String date, String time, Venue venue, double ticketPrice, int totalSeats,
			int availableSeats) {
		// TODO Auto-generated constructor stub
	}







	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
     

}
