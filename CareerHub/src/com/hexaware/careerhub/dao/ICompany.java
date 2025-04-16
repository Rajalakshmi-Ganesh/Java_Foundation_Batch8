package com.hexaware.careerhub.dao;

import java.util.*;

import com.hexaware.careerhub.entity.Company;
import com.hexaware.careerhub.exception.DatabaseConnectionException;

public interface ICompany {
	
	
	boolean addCompany(Company company) throws DatabaseConnectionException;
	
    List<Company> getAllCompanies();
    
    Company getCompanyById(int id);
}
