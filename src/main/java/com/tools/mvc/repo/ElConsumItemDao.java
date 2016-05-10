package com.tools.mvc.repo;

import java.util.Date;
import java.util.List;

import com.tools.mvc.domain.ElConsumItem;

public interface ElConsumItemDao {

	public int findToatalConsumItemUsage(int custId, int NumOfDays);
	
	public int findToatalConsumItemUsage(int custId, Date fromDate, Date toDate);
	
	public List<Object[]> findMaximumConsumer();
	
	public List<Object[]> findMinimumConsumer();
	
	public List<ElConsumItem> findListItemByConsum(int consId);
	
	public List<Object[]> findTotalConsumtionList();
	
	public int totalUnitsCurMonth();
	
	public int totalUnitsPrewMonth();
	
	public int totUnitPerDateCurrMon(Date date);
}
