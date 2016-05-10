package com.tools.mvc.repo;

import java.util.List;

import com.tools.mvc.domain.ElConsum;

public interface ElConsumDao {
	
	public List<ElConsum> getListConsumByCusId(int cusId);

}
