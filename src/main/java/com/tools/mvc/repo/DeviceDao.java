package com.tools.mvc.repo;

import java.util.List;

public interface DeviceDao {

	public String insertDeviceData(int custId,String deviceDtl);
	public Object findMaxConsumer();
	public String insertDeviceData1(int custId,int item ,int usage );
	public List<Object[]> findConsumerUnits(String p_cust,String p_hour,int p_consID);
}