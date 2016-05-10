package com.tools.mvc.repo;

public interface ElGroupDao {
	
	public String saveGroup(int user_id, String desc);
	
	public int selectMaxGrpId();

}
