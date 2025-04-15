package com.hexaware.ticketbookingsyste.conditionalstatement;

import java.util.Scanner;

public class Task1 {
	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter the total number of tickets available:");
		int availableTickets = sc.nextInt();
		
		System.out.println("Enter the total number of tickets to book:");
		int noOfBookingTickets = sc.nextInt();
		
		int remainingTickets =availableTickets;
		
		if(availableTickets>=noOfBookingTickets)
		{
			remainingTickets = availableTickets-noOfBookingTickets;
			System.out.println("Total number of tickets remaining:"+ remainingTickets);
		}
		else {
			System.out.println("Required number of tickets is unavailable. Only "+ remainingTickets+" is left");
		}
		
	}

}
