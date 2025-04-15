package com.hexaware.ticketbookingsystem.entity;

import java.sql.Date;
import java.sql.Time;


public class Sports extends Event{
	
	private String sportName;  
    private String teamsName;
    
	public Sports() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Sports(int eventId, String eventName, Date eventDate, Time eventTime, String venueName, int totalSeats,
			int availableSeats, double ticketPrice, String eventType,String sportName, String teamsName) {
		super(eventId, eventName, eventDate, eventTime, venueName, totalSeats, availableSeats, ticketPrice,"Sports");
		// TODO Auto-generated constructor stub
		this.sportName = sportName;
	    this.teamsName = teamsName;
	}



	public Sports(int eventId, String name, String date, String time, Venue venue, double ticketPrice, int totalSeats,
			int availableSeats) {
		// TODO Auto-generated constructor stub
	}



	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public String getTeamsName() {
		return teamsName;
	}

	public void setTeamsName(String teamsName) {
		this.teamsName = teamsName;
	} 
    
	

}
