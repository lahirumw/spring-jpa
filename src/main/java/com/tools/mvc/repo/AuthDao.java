package com.tools.mvc.repo;

public interface AuthDao {

	public String getError();

	public boolean authenticate(String username, String password);

	public String getUserCategory(String username);
	
}
