package com.tools.mvc.repo;

import java.util.List;

import com.tools.mvc.domain.ElCustomer;

public interface ElCustomerDao {
	
	public ElCustomer getElCustomerById(int custId); 
	
	public List<ElCustomer> getElCustomerInfomation(String custName);

	public List<String> getRandomCustomerSearch(String param);

public List<ElCustomer> getAll();
}
