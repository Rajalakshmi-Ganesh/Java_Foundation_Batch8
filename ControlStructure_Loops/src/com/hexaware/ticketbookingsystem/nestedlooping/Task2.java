package com.hexaware.ticketbookingsystem.nestedlooping;

import java.util.Scanner;

public class Task2 {
	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Ticket Booking System");
		System.out.println("Available ticket categories: Gold, Silver, Diamond");
		System.out.println("Enter the ticket type:");
		
		String ticketType = sc.nextLine();
		
		int price = 0;
		int noOfTickets = 0;
		int totalPrice = 0;
		
		switch(ticketType)
		{
		case "Gold":
			price = 200;
			break;
			
		case "silver":
			price = 100;
			break;
			
		case "Diamond":
			price = 400;
			break;
		default:
			System.out.println("Invalid ticket type.");
			break;
		}
		
		if(price!=0)
		{
			System.out.println("Enter number of tickets:");
			noOfTickets = sc.nextInt();
			totalPrice = noOfTickets* price;
		}
		
		System.out.println("Price of "+ ticketType+" ticket is "+price);
		System.out.println("Total price of "+noOfTickets+" tickets is " +totalPrice);

		
	}

}
