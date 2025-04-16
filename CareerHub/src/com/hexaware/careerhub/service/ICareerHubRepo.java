package com.hexaware.careerhub.service;

import java.util.List;

import com.hexaware.careerhub.entity.*;
import com.hexaware.careerhub.exception.ApplicationDeadlineException;
import com.hexaware.careerhub.exception.DatabaseConnectionException;
import com.hexaware.careerhub.exception.InvalidEmailFormatException;
import com.hexaware.careerhub.exception.SalaryCalculationException;

public interface ICareerHubRepo {

	
	boolean insertApplicant(Applicant applicant) throws InvalidEmailFormatException,DatabaseConnectionException ;
	
    Applicant getApplicantById(int applicantId);
    
    List<Applicant> getAllApplicants();
    
    boolean addCompany(Company company) throws DatabaseConnectionException;
    
    Company getCompanyById(int companyId);
    
    List<Company> getAllCompanies();
    
    boolean insertJobListing(JobListing job) throws SalaryCalculationException,DatabaseConnectionException;
    
    List<JobListing> getAllJobListings();
    
    List<JobListing> getJobListingsByCompanyId(int companyId);
    
    JobListing getJobListingById(int jobId);
    
    boolean insertJobApplication(JobApplication application) throws ApplicationDeadlineException,DatabaseConnectionException;
    
    List<JobApplication> getApplicationsByJobId(int jobId);
    
    List<JobApplication> getAllApplications();
    
    List<JobListing> searchJobsBySalaryRange(double minSalary, double maxSalary);

}
