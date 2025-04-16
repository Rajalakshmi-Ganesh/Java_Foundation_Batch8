package com.hexaware.careerhub.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.careerhub.entity.JobListing;
import com.hexaware.careerhub.exception.DatabaseConnectionException;
import com.hexaware.careerhub.exception.SalaryCalculationException;

public class JobListingImpl implements IJobListing{

	@Override
	public boolean insertJobListing(JobListing job) throws SalaryCalculationException,DatabaseConnectionException{
		// TODO Auto-generated method stub
		
		if (job.getSalary() <= 0) {
			
            throw new SalaryCalculationException("Invalid salary amount: Salary must be greater than zero.");
        }
		
        try (Connection conn = DBUtil.getDBConnection()) {
        	
        	String query = "INSERT INTO Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        	
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, job.getCompanyID());
            ps.setString(2, job.getJobTitle());
            ps.setString(3, job.getJobDescription());
            ps.setString(4, job.getJobLocation());
            ps.setDouble(5, job.getSalary());
            ps.setString(6, job.getJobType());
            ps.setDate(7, new Date(job.getPostedDate().getTime()));
            ps.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
        	
        	throw new DatabaseConnectionException("Failed to connect to DB .", e);
        }
		
	}

	@Override
	public List<JobListing> getAllJobListings() {
		// TODO Auto-generated method stub
		
		List<JobListing> listings = new ArrayList<JobListing>();
       
        try (Connection conn = DBUtil.getDBConnection()) {
        	
        	String query = "SELECT * FROM Jobs";
        	
        	PreparedStatement ps = conn.prepareStatement(query);
        	
        	ResultSet rs = ps.executeQuery();
        	
            while (rs.next()) {
            	
            	JobListing listing = null;
            	
            	listing = new JobListing(rs.getInt("JobID"),rs.getInt("CompanyID"),rs.getString("JobTitle"),rs.getString("JobDescription"),
	                    rs.getString("JobLocation"),
	                    rs.getDouble("Salary"),
	                    rs.getString("JobType"),
	                    rs.getDate("PostedDate"));
            	
            	listings.add(listing);
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listings;
		
	}

	@Override
	public List<JobListing> getJobListingsByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		List<JobListing> listings = new ArrayList<JobListing>();
		
		try (Connection conn = DBUtil.getDBConnection()) {
			
			String query = "SELECT * FROM Jobs WHERE CompanyID = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, companyId);
			
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	
            	JobListing listing = null;
            	
            	listing = new JobListing(rs.getInt("JobID"),rs.getInt("CompanyID"),rs.getString("JobTitle"),rs.getString("JobDescription"),
	                    rs.getString("JobLocation"),
	                    rs.getDouble("Salary"),
	                    rs.getString("JobType"),
	                    rs.getDate("PostedDate"));
            	
            	listings.add(listing);
            	
            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listings;
	}

	@Override
	public JobListing getJobListingById(int jobId) {
		// TODO Auto-generated method stub
		JobListing job = null;
		
		try (Connection conn = DBUtil.getDBConnection()) {
			
	        String query = "SELECT * FROM Jobs WHERE JobID = ?";
			
	        PreparedStatement ps = conn.prepareStatement(query);
	        
	        ps.setInt(1, jobId);
	        
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
            	
            	job = new JobListing(rs.getInt("JobID"),rs.getInt("CompanyID"),rs.getString("JobTitle"),rs.getString("JobDescription"),
	                    rs.getString("JobLocation"),
	                    rs.getDouble("Salary"),
	                    rs.getString("JobType"),
	                    rs.getDate("PostedDate"));
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return job;
	}

	@Override
	public List<JobListing> searchJobsBySalaryRange(double minSalary, double maxSalary) {
		// TODO Auto-generated method stub
		List<JobListing> listings = new ArrayList<JobListing>();
		
		try (Connection conn = DBUtil.getDBConnection()) {
			
			String query = "SELECT * FROM Jobs WHERE salary BETWEEN ? AND ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setDouble(1, minSalary);
	        ps.setDouble(2, maxSalary);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
            	
            	JobListing listing = null;
            	
            	listing = new JobListing(rs.getInt("JobID"),rs.getInt("CompanyID"),rs.getString("JobTitle"),rs.getString("JobDescription"),
	                    rs.getString("JobLocation"),
	                    rs.getDouble("Salary"),
	                    rs.getString("JobType"),
	                    rs.getDate("PostedDate"));
            	
            	listings.add(listing);
            	
            }
	        
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listings;
	}
	
	
	
}
