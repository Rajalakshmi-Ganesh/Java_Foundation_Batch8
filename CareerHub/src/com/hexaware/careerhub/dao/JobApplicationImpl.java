package com.hexaware.careerhub.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hexaware.careerhub.entity.JobApplication;
import com.hexaware.careerhub.exception.ApplicationDeadlineException;
import com.hexaware.careerhub.exception.DatabaseConnectionException;

public class JobApplicationImpl implements IJobApplication{


	@Override
	public boolean insertJobApplication(JobApplication application) throws ApplicationDeadlineException,DatabaseConnectionException{
		// TODO Auto-generated method stub
		try (Connection conn = DBUtil.getDBConnection()){
			
			String postedDateQuery = "SELECT PostedDate FROM JobListing WHERE JobID = ?";
			
	        PreparedStatement deadlinePs = conn.prepareStatement(postedDateQuery);
	        
	        deadlinePs.setInt(1, application.getJobID());
	        
	        ResultSet rs = deadlinePs.executeQuery();
	        
	        if (rs.next()) {
	        	
	            java.sql.Date postedDate = rs.getDate("PostedDate");

	            Calendar cal = Calendar.getInstance();
	            
	            cal.setTime(postedDate);
	            
	            cal.add(Calendar.MONTH, 1);
	            
	            java.sql.Date deadline = new java.sql.Date(cal.getTimeInMillis());
	            
	            java.sql.Date appDate = new java.sql.Date(application.getApplicationDate().getTime());

	            if (appDate.after(deadline)) {
	            	
	                throw new ApplicationDeadlineException("Application date is after the deadline!");
	            }
	        } else {
	        	
	            System.out.println("No job found for JobID: " + application.getJobID());
	            
	            return false;
	        }
			
			String query = "INSERT INTO Applications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, application.getJobID());
            ps.setInt(2, application.getApplicantID());
            ps.setDate(3, new java.sql.Date(application.getApplicationDate().getTime()));
            ps.setString(4, application.getCoverLetter());
            ps.executeUpdate();
            
            return true;
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseConnectionException("Failed to connect to DB .", e);
		}
		
	}

	@Override
	public List<JobApplication> getApplicationsByJobId(int jobId) {
		// TODO Auto-generated method stub
		
		List<JobApplication> applications = new ArrayList<JobApplication>();
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "SELECT * FROM Applications WHERE JobID = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, jobId);
			
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	
            	JobApplication application = null;
            	
            	application = new JobApplication(rs.getInt("ApplicationID"),rs.getInt("JobID"),rs.getInt("ApplicantID"),rs.getDate("ApplicationDate"),rs.getString("CoverLetter"));
            
            	applications.add(application);
            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applications;
	}

	@Override
	public List<JobApplication> getAllApplications() {
		// TODO Auto-generated method stub
		
		List<JobApplication> applications = new ArrayList<JobApplication>();
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "SELECT * FROM Applications";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
            ResultSet rs = ps.executeQuery();
            
	        while(rs.next()) {
	        	
	        	JobApplication application = null;
            	
            	application = new JobApplication(rs.getInt("ApplicationID"),rs.getInt("JobID"),rs.getInt("ApplicantID"),rs.getDate("ApplicationDate"),rs.getString("CoverLetter"));
            
            	applications.add(application);
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return applications;
	}

}
