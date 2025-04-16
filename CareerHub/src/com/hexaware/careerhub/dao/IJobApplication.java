package com.hexaware.careerhub.dao;

import java.util.List;

import com.hexaware.careerhub.entity.JobApplication;
import com.hexaware.careerhub.exception.ApplicationDeadlineException;
import com.hexaware.careerhub.exception.DatabaseConnectionException;

public interface IJobApplication {

	
	boolean insertJobApplication(JobApplication application) throws ApplicationDeadlineException,DatabaseConnectionException;
	
    List<JobApplication> getApplicationsByJobId(int jobId);
    
    List<JobApplication> getAllApplications();
}
