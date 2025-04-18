package com.hexaware.ticketbookingsystem.entity;

public class Customer {
	
	private int customerId;
    private String customerName;
    private String email;
    private String phoneNumber;
    
    public Customer() {};
    
    
	public Customer(int customerId, String customerName, String email, String phoneNumber) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Customer(String name, String email, String phone) {
		// TODO Auto-generated constructor stub
		this.customerName = name;
		this.email = email;
		this.phoneNumber = phone;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + "]";
	}
    
    

}
