package com.hexaware.ticketbookingsystem.entity;

import java.sql.Date;
import java.sql.Time;

public abstract class Event {
	
    private int eventId;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String venueName;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private String eventType;
    
    public Event() {};
    
    
	public Event(int eventId, String eventName, Date eventDate, Time eventTime, String venueName, int totalSeats,
			int availableSeats, double ticketPrice, String eventType) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.venueName = venueName;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.ticketPrice = ticketPrice;
		this.eventType = eventType;
	}
	
	
	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	public Time getEventTime() {
		return eventTime;
	}
	
	public void setEventTime(Time eventTime) {
		this.eventTime = eventTime;
	}
	
	public String getVenueName() {
		return venueName;
	}
	
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	public int getTotalSeats() {
		return totalSeats;
	}
	
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}
	
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	public double getTicketPrice() {
		return ticketPrice;
	}
	
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventDate=" + eventDate + ", eventTime="
				+ eventTime + ", venueName=" + venueName + ", totalSeats=" + totalSeats + ", availableSeats="
				+ availableSeats + ", ticketPrice=" + ticketPrice + ", eventType=" + eventType + "]";
	}

	

}
