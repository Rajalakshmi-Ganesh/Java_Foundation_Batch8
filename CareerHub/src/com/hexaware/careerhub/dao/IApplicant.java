package com.hexaware.careerhub.dao;

import java.util.List;

import com.hexaware.careerhub.entity.Applicant;
import com.hexaware.careerhub.exception.DatabaseConnectionException;
import com.hexaware.careerhub.exception.InvalidEmailFormatException;


public interface IApplicant {

	
	boolean insertApplicant(Applicant applicant) throws InvalidEmailFormatException,DatabaseConnectionException;
	
    Applicant getApplicantById(int applicantId);
    
    List<Applicant> getAllApplicants();
    
}
