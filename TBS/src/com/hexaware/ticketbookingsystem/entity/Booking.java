package com.hexaware.ticketbookingsystem.entity;

import java.sql.Date;
import java.util.Objects;

public class Booking {
	
	 	private int bookingId;
	    private int customerId;
	    private int eventId;
	    private int numTickets;
	    private double totalCost;
	    private Date bookingDate;
	   
	   
	    
	    public Booking() {};
	    

		public Booking(int bookingId, int customerId, int eventId, int numTickets, double totalCost, Date bookingDate) {
			super();
			this.bookingId = bookingId;
			this.customerId = customerId;
			this.eventId = eventId;
			this.numTickets = numTickets;
			this.totalCost = totalCost;
			this.bookingDate = bookingDate;
		}
		
		public Booking(int customerId, int eventId, int numTickets, double totalCost, Date bookingDate) {
			super();
			
			this.customerId = customerId;
			this.eventId = eventId;
			this.numTickets = numTickets;
			this.totalCost = totalCost;
			this.bookingDate = bookingDate;
		}

	

		public int getBookingId() {
			return bookingId;
		}


		public void setBookingId(int bookingId) {
			this.bookingId = bookingId;
		}


		public int getCustomerId() {
			return customerId;
		}


		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}


		public int getEventId() {
			return eventId;
		}


		public void setEventId(int eventId) {
			this.eventId = eventId;
		}


		public int getNumTickets() {
			return numTickets;
		}


		public void setNumTickets(int numTickets) {
			this.numTickets = numTickets;
		}


		public double getTotalCost() {
			return totalCost;
		}


		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}


		public Date getBookingDate() {
			return bookingDate;
		}


		public void setBookingDate(Date bookingDate) {
			this.bookingDate = bookingDate;
		}
		
		    
		@Override
		public int hashCode() {
			return Objects.hash(bookingDate, bookingId, customerId, eventId, numTickets, totalCost);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Booking other = (Booking) obj;
			return Objects.equals(bookingDate, other.bookingDate) && bookingId == other.bookingId
					&& customerId == other.customerId && eventId == other.eventId && numTickets == other.numTickets
					&& Double.doubleToLongBits(totalCost) == Double.doubleToLongBits(other.totalCost);
		}


		@Override
		public String toString() {
			return "Booking [bookingId=" + bookingId + ", customerId=" + customerId + ", eventId=" + eventId
					+ ", numTickets=" + numTickets + ", totalCost=" + totalCost + ", bookingDate=" + bookingDate + "]";
		}
		
	    


}
