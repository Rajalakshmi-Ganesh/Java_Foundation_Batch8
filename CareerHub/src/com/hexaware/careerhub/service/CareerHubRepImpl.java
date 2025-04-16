package com.hexaware.careerhub.service;

import java.util.List;

import com.hexaware.careerhub.dao.*;
import com.hexaware.careerhub.entity.Applicant;
import com.hexaware.careerhub.entity.Company;
import com.hexaware.careerhub.entity.JobApplication;
import com.hexaware.careerhub.entity.JobListing;
import com.hexaware.careerhub.exception.ApplicationDeadlineException;
import com.hexaware.careerhub.exception.DatabaseConnectionException;
import com.hexaware.careerhub.exception.InvalidEmailFormatException;
import com.hexaware.careerhub.exception.SalaryCalculationException;

public class CareerHubRepImpl implements ICareerHubRepo{

	ApplicantImpl  applicants = new ApplicantImpl();
	
	CompanyImpl company = new CompanyImpl();
	
	JobListingImpl jobs = new JobListingImpl();
	
	JobApplicationImpl applications = new JobApplicationImpl();
	
	@Override
	public boolean insertApplicant(Applicant applicant) throws InvalidEmailFormatException,DatabaseConnectionException{
		// TODO Auto-generated method stub
		try {
			return applicants.insertApplicant(applicant);
			
		} catch (InvalidEmailFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Applicant getApplicantById(int applicantId) {
		// TODO Auto-generated method stub
		try {
			 return applicants.getApplicantById(applicantId);
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Applicant> getAllApplicants() {
		// TODO Auto-generated method stub
		try {
			
			return applicants.getAllApplicants();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addCompany(Company company1) throws DatabaseConnectionException{
		// TODO Auto-generated method stub
		try {
			return company.addCompany(company1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Company getCompanyById(int companyId) {
		// TODO Auto-generated method stub
		
		try {
			return company.getCompanyById(companyId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		try {
			
			return company.getAllCompanies();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertJobListing(JobListing job) throws SalaryCalculationException,DatabaseConnectionException{
		// TODO Auto-generated method stub
		try {
			return jobs.insertJobListing(job);
			
		} catch (SalaryCalculationException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<JobListing> getAllJobListings() {
		// TODO Auto-generated method stub
		try {
			
			return jobs.getAllJobListings();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<JobListing> getJobListingsByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		try {
			
			return jobs.getJobListingsByCompanyId(companyId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JobListing getJobListingById(int jobId) {
		// TODO Auto-generated method stub
		try {
			
			return jobs.getJobListingById(jobId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertJobApplication(JobApplication application) throws ApplicationDeadlineException,DatabaseConnectionException{
		// TODO Auto-generated method stub
		try {
			return applications.insertJobApplication(application);
			
		} catch (ApplicationDeadlineException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<JobApplication> getApplicationsByJobId(int jobId) {
		// TODO Auto-generated method stub
		try {
			
			return applications.getApplicationsByJobId(jobId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<JobApplication> getAllApplications() {
		// TODO Auto-generated method stub
		try {
			
			return applications.getAllApplications();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<JobListing> searchJobsBySalaryRange(double minSalary, double maxSalary) {
		// TODO Auto-generated method stub
		try {
			
			return jobs.searchJobsBySalaryRange(minSalary, maxSalary);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
