package com.hexaware.careerhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.careerhub.entity.Company;
import com.hexaware.careerhub.exception.DatabaseConnectionException;
import com.hexaware.careerhub.dao.DBUtil;

public class CompanyImpl implements ICompany{

	@Override
	public boolean addCompany(Company company) throws  DatabaseConnectionException{
		// TODO Auto-generated method stub
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "INSERT INTO Companies (CompanyName, Location) VALUES (?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, company.getCompanyName());
            ps.setString(2, company.getLocation());
            ps.executeUpdate();
            
            return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseConnectionException("Failed to connect to DB .", e);
		}
		
		
		
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		
		 List<Company> companies = new ArrayList<Company>();
		 
		 try (Connection conn = DBUtil.getDBConnection()){
			 
	     String query = "SELECT * FROM Company";
	     
	     PreparedStatement ps = conn.prepareStatement(query);
	     
	     ResultSet rs = ps.executeQuery();
	     
	     while(rs.next()) {
	    	 
	    	 Company c = new Company(rs.getInt("CompanyID"),rs.getString("CompanyName"),rs.getString("Location"));
	    	 
	    	 companies.add(c);
	     }
	     
	     
	     } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return companies;
	}

	@Override
	public Company getCompanyById(int id) {
		// TODO Auto-generated method stub
		
		Company company = null;
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "SELECT * FROM Companies WHERE CompanyID = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				company = new Company(rs.getInt("CompanyID"),rs.getString("CompanyName"),rs.getString("Location"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

}
