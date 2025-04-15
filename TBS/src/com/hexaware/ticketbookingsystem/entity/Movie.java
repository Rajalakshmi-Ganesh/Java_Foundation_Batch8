package com.hexaware.ticketbookingsystem.entity;

import java.sql.Date;
import java.sql.Time;


public class Movie extends Event{
	
	private String genre;
	private String actorName;
	private String actressName;
	
	
	
	
	public Movie(int eventId, String eventName, Date eventDate, Time eventTime, String venueName, int totalSeats,
			int availableSeats, double ticketPrice, String eventType,String genre, String actorName,String actressName) {
		super(eventId, eventName, eventDate, eventTime, venueName, totalSeats, availableSeats, ticketPrice, "Movie");
		// TODO Auto-generated constructor stub
		this.genre = genre;
        this.actorName = actorName;
        this.actressName = actressName;
	}

	public Movie() {
		super();
		
	}

	public Movie(int eventId, String name, String date, String time, Venue venue, double ticketPrice, int totalSeats,
			int availableSeats) {
		// TODO Auto-generated constructor stub
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActressName() {
		return actressName;
	}

	public void setActressName(String actressName) {
		this.actressName = actressName;
	}
	
	
	

}
