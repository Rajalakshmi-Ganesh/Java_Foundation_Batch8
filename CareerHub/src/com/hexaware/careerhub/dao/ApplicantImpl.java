package com.hexaware.careerhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.careerhub.entity.Applicant;
import com.hexaware.careerhub.exception.DatabaseConnectionException;
import com.hexaware.careerhub.exception.InvalidEmailFormatException;

public class ApplicantImpl implements IApplicant{

	
	@Override
	public boolean insertApplicant(Applicant applicant) throws InvalidEmailFormatException, DatabaseConnectionException{
		// TODO Auto-generated method stub
		
		String email = applicant.getEmail();
		
        if (!email.toLowerCase().matches("^[a-z]+@gmail\\.com$")) {
        	
            throw new InvalidEmailFormatException("Invalid email format.");
            
        }
		
        try (Connection conn = DBUtil.getDBConnection()) {
        	
        	String query = "INSERT INTO Applicants (FirstName, LastName, Email, Phone, Resume) VALUES (?, ?, ?, ?, ?)"; 
        	
        	PreparedStatement ps = conn.prepareStatement(query);
        	
            ps.setString(1, applicant.getFirstName());
            ps.setString(2, applicant.getLastName());
            ps.setString(3, applicant.getEmail());
            ps.setString(4, applicant.getPhone());
            ps.setString(5, applicant.getResume());
            ps.executeUpdate();
            
            return true;
            
        } 
        catch (SQLException e) {
        	throw new DatabaseConnectionException("Failed to connect to DB .", e);
        }
		
	}

	@Override
	public Applicant getApplicantById(int applicantId) {
		// TODO Auto-generated method stub
		
		Applicant applicant = null;
        
        try (Connection conn = DBUtil.getDBConnection()) {
        	
        	String query = "SELECT * FROM Applicants WHERE ApplicantID = ?";
        	
        	PreparedStatement ps = conn.prepareStatement(query);
        			
            ps.setInt(1, applicantId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	
                applicant = new Applicant(rs.getInt("ApplicantID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Email"),rs.getString("Phone"),rs.getString("Resume"));
            
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicant;
		
	}

	@Override
	public List<Applicant> getAllApplicants() {
		// TODO Auto-generated method stub
		
		List<Applicant> applicants = new ArrayList<Applicant>();
        
        
        try (Connection conn = DBUtil.getDBConnection()){
        	
        	String query = "SELECT * FROM Applicants";
        	
        	PreparedStatement ps = conn.prepareStatement(query);
        	
        	ResultSet rs = ps.executeQuery();	
        	
            while (rs.next()) {
            	
            	Applicant applicant = null;
            	
            	applicant = new Applicant(rs.getInt("ApplicantID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Email"),rs.getString("Phone"),rs.getString("Resume"));
            
            	applicants.add(applicant);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return applicants;
		
	}

}
