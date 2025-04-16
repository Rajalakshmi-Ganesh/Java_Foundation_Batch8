package com.hexaware.careerhub.presentation;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.hexaware.careerhub.entity.*;
import com.hexaware.careerhub.exception.*;
import com.hexaware.careerhub.service.*;

public class CareerHubUI {

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        
        ICareerHubRepo system = new CareerHubRepImpl();
        
        int choice;

        do {
        	
            System.out.println("CareerHub Job Board");
            System.out.println("1. Add Applicant");
            System.out.println("2. Add Company");
            System.out.println("3. Add Job Listing");
            System.out.println("4. Apply for Job");
            System.out.println("5. View All Job Listings");
            System.out.println("6. View All Job Applications");
            System.out.println("7. Search Job Listings by Salary Range");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();
            sc.nextLine();

            try {
            	
                switch (choice) {
                
                    case 1:
                    	
                        System.out.println("Enter applicant details:");
                        System.out.print("ID: ");
                        
                        int applicantId = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.print("First Name: ");
                        String firstName = sc.nextLine();
                        
                        System.out.print("Last Name: ");
                        String lastName = sc.nextLine();
                        
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        
                        System.out.print("Phone: ");
                        String phone = sc.nextLine();
                        
                        System.out.print("Resume Text: ");
                        String resume = sc.nextLine();

                        Applicant applicant = new Applicant(applicantId, firstName, lastName, email, phone, resume);
                        
                        if (system.insertApplicant(applicant)) {
                        	
                            System.out.println("Applicant inserted successfully.");
                        }
                        break;

                    case 2:
                    	
                        System.out.println("Enter company details:");
                        System.out.print("Company ID: ");
                        
                        int companyId = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.print("Company Name: ");
                        String companyName = sc.nextLine();
                        
                        System.out.print("Location: ");
                        String location = sc.nextLine();

                        Company company = new Company(companyId, companyName, location);
                        
                        if (system.addCompany(company)) {
                        	
                            System.out.println("Company added successfully.");
                        }
                        break;

                    case 3:
                    	
                        System.out.println("Enter job listing details:");
                        System.out.print("Job ID: ");
                        
                        int jobId = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.print("Company ID: ");
                        int jobCompanyId = sc.nextInt();  
                        sc.nextLine();
                        
                        System.out.print("Job Title: ");
                        String jobTitle = sc.nextLine();
                        
                        System.out.print("Job Description: ");
                        String jobDesc = sc.nextLine();
                        
                        System.out.print("Job Location: ");
                        String jobLoc = sc.nextLine();
                        
                        System.out.print("Salary: ");
                        double salary = sc.nextDouble();
                        sc.nextLine();
                        
                        System.out.print("Job Type: ");
                        String jobType = sc.nextLine();
                        
                        System.out.print("Posted Date: ");
                        String postedDate = sc.nextLine();

                        JobListing job = new JobListing(jobId, jobCompanyId, jobTitle, jobDesc, jobLoc, salary, jobType, Date.valueOf(postedDate));
                        
                        if (system.insertJobListing(job)) {
                        	
                            System.out.println("Job listing inserted successfully.");
                        }
                        break;

                    case 4:
                    	
                        System.out.println("Enter job application details:");
                        System.out.print("Application ID: ");
                        
                        int appId = sc.nextInt();
                        
                        System.out.print("Job ID: ");
                        int appliedJobId = sc.nextInt();
                        
                        System.out.print("Applicant ID: ");
                        int appApplicantId = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.print("Cover Letter: ");
                        String coverLetter = sc.nextLine();
                        
                        System.out.print("Application Date: ");
                        String appDate = sc.nextLine();

                        JobApplication application = new JobApplication(appId, appliedJobId, appApplicantId, Date.valueOf(appDate), coverLetter);
                        
                        if (system.insertJobApplication(application)) {
                        	
                            System.out.println(" Job application submitted successfully.");
                        }
                        break;

                    case 5:
                    	
                        System.out.println("All Job Listings:");
                        
                        List<JobListing> jobList = system.getAllJobListings();
                        
                        for (JobListing j : jobList) {
                        	
                            System.out.println(j);
                        }
                        break;

                    case 6:
                    	
                        System.out.println("All Job Applications:");
                        
                        List<JobApplication> appList = system.getAllApplications();
                        
                        for (JobApplication ja : appList) {
                        	
                            System.out.println(ja);
                        }
                        break;
                        
                    case 7:
                    	
                        System.out.print("Enter minimum salary: ");
                        double minSalary = sc.nextDouble();
                        
                        System.out.print("Enter maximum salary: ");
                        double maxSalary = sc.nextDouble();

                        List<JobListing> filteredJobs = system.searchJobsBySalaryRange(minSalary, maxSalary);
                        
                        if (filteredJobs.isEmpty()) {
                            System.out.println("No job listings found in this salary range.");
                        } 
                        else {
                        	
                            System.out.println("Job Listings within salary range:");
                            
                            for (JobListing jl : filteredJobs) {
                            	
                                System.out.println(jl);
                            }
                        }
                        break;

                    case 0:
                    	
                        System.out.println("Thanks for visiting!");
                        break;

                    default:
                    	
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (InvalidEmailFormatException | SalaryCalculationException | ApplicationDeadlineException | DatabaseConnectionException e) {
            	
                System.out.println("Error: " + e.getMessage());
                
            } catch (Exception e) {
            	
                System.out.println("Unexpected error: " + e.getMessage());
                
                e.printStackTrace();
            }

        } while (choice != 0);

        
    }
}
