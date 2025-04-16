package com.hexaware.careerhub.dao;

import java.util.List;

import com.hexaware.careerhub.entity.JobListing;
import com.hexaware.careerhub.exception.DatabaseConnectionException;
import com.hexaware.careerhub.exception.SalaryCalculationException;

public interface IJobListing {

	
	boolean insertJobListing(JobListing job) throws SalaryCalculationException,DatabaseConnectionException;
	
    List<JobListing> getAllJobListings();
    
    List<JobListing> getJobListingsByCompanyId(int companyId);
    
    JobListing getJobListingById(int jobId);
    
    List<JobListing> searchJobsBySalaryRange(double minSalary, double maxSalary) ;

}
