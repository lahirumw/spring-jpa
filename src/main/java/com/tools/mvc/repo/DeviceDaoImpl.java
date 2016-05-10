package com.tools.mvc.repo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tools.mvc.domain.ElConsumItem;
import com.tools.mvc.domain.ElCustomer;

@Transactional
@Repository
public class DeviceDaoImpl implements DeviceDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ElCustomerDao elCustomerDao;

	public String insertDeviceData(int custId,String deviceDtl) {
		String resutl = null;
		int unitId = 0;
		int unitUsage = 0;
		int length = 0;
		int k =1;
		//int custId;

		try {
			String[] deviceDtlArray = deviceDtl.split(",");
			length = deviceDtlArray.length;
			System.out.println("*******length*****"+length);
			if (length == 0) {

				resutl = "F";
			} else if (length > 0) {

				//custId = Integer.parseInt(deviceDtlArray[0]);
				ElCustomer elCustomer = elCustomerDao.getElCustomerById(custId);

				if (elCustomer.equals(null)) {
					resutl = "F";
				} else {
					

					for (int i = 0; i < length; i++) {
						System.out.println("*******i*****"+i);
						unitId = Integer.parseInt(deviceDtlArray[i]);
						System.out.println("*******unitId*****"+unitId);
						unitUsage = Integer.parseInt(deviceDtlArray[i + 1]);
						System.out.println("*******unitUsage*****"+unitUsage);
						i = i + 1;
						ElConsumItem elConsumItem = new ElConsumItem();
						//elConsumItem.setItemId(unitId);
						elConsumItem.setConsItemId(unitId);
						elConsumItem.setItemUsage(unitUsage);
						elConsumItem.setCreatedDate(new Date());
						elConsumItem.setCreatedUser("udayanga");
						elConsumItem.setConsTime(new Date());
						
						Object objMax = findMaxConsumer();
						int id_no= (Integer) objMax;
						elConsumItem.setItemId(id_no+1);
						//model.addAttribute("maxConVal", objMax.get(0)[0]);
						em.merge(elConsumItem);
						elConsumItem =null;
						resutl = "Y";
						break;
						//k=3;
					}
					/*
					for (int i = 2; i < length; i++) {
						System.out.println("*******i*****"+i);
						unitId = Integer.parseInt(deviceDtlArray[i]);
						System.out.println("*******unitId*****"+unitId);
						unitUsage = Integer.parseInt(deviceDtlArray[i + 1]);
						System.out.println("*******unitUsage*****"+unitUsage);
						i = i + 1;
						ElConsumItem elConsumItem = new ElConsumItem();
						//elConsumItem.setItemId(unitId);
						elConsumItem.setConsItemId(unitId);
						elConsumItem.setItemUsage(unitUsage);
						elConsumItem.setCreatedDate(new Date());
						elConsumItem.setCreatedUser("udayanga");
						elConsumItem.setConsTime(new Date());
						
						Object objMax = findMaxConsumer();
						int id_no= (Integer) objMax;
						elConsumItem.setItemId(id_no+1);
						//model.addAttribute("maxConVal", objMax.get(0)[0]);
						em.merge(elConsumItem);
						elConsumItem =null;
						resutl = "Y";
						break;
						//k=3;
					}
					
					for (int i = 4; i < length; i++) {
						System.out.println("*******i*****"+i);
						unitId = Integer.parseInt(deviceDtlArray[i]);
						System.out.println("*******unitId*****"+unitId);
						unitUsage = Integer.parseInt(deviceDtlArray[i + 1]);
						System.out.println("*******unitUsage*****"+unitUsage);
						i = i + 1;
						ElConsumItem elConsumItem = new ElConsumItem();
						//elConsumItem.setItemId(unitId);
						elConsumItem.setConsItemId(unitId);
						elConsumItem.setItemUsage(unitUsage);
						elConsumItem.setCreatedDate(new Date());
						elConsumItem.setCreatedUser("udayanga");
						elConsumItem.setConsTime(new Date());
						
						Object objMax = findMaxConsumer();
						int id_no= (Integer) objMax;
						elConsumItem.setItemId(id_no+1);
						//model.addAttribute("maxConVal", objMax.get(0)[0]);
						em.merge(elConsumItem);
						elConsumItem =null;
						resutl = "Y";
						break;
						//k=3;
					} */
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "F";
		}

		return resutl;
	}
	
	
	public String insertDeviceData1(int custId,int item ,int usage ) {
		
		try{
	ElConsumItem elConsumItem = new ElConsumItem();
	//elConsumItem.setItemId(unitId);
	elConsumItem.setConsItemId(item);
	elConsumItem.setItemUsage(usage);
	elConsumItem.setCreatedDate(new Date());
	elConsumItem.setCreatedUser("udayanga");
	elConsumItem.setConsTime(new Date());
	
	Object objMax = findMaxConsumer();
	int id_no= (Integer) objMax;
	elConsumItem.setItemId(id_no+1);
	//model.addAttribute("maxConVal", objMax.get(0)[0]);
	em.merge(elConsumItem);
	elConsumItem = null;
	return "Y";
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		return "F";
	}

	
	}
	
	
	public Object findMaxConsumer() {
		System.out.println("*************  Inside the findMinimumConsumer  ************");
		try{
			String sql = "select max(el_consum_item.item_id)  from el_consum_item ";
					
			Query query = em.createNativeQuery(sql);		
			return query.getSingleResult();
			
			
		}catch(NullPointerException e1){
			e1.printStackTrace();
			System.out.println(e1.getMessage());
			return null;
		}catch(Exception e2){
			e2.printStackTrace();
			System.out.println("**********exception***********");
			System.out.println(e2.getMessage());
			return null;
		}

} 
	
	public List<Object[]> findConsumerUnits(String p_cust,String p_hour,int p_consID) {
		System.out.println("*************  Inside the findConsumerUnits  ************");
		try{
			String sql = "select el_consum.cons_id,el_consum.cons_Item,sum(el_consum_item.item_usage) item_usage from el_customer, el_consum, el_consum_item "+ 
	                      " where el_customer.cust_id=el_consum.cust_id and el_consum.cons_id = el_consum_item.cons_item_id "+
	                      " and el_customer.cust_id = " + "'"+p_cust + "'" +
	                      " and HOUR(el_consum_item.cons_Time) = " + "'"+p_hour + "'" +
	                      " and el_consum.cons_id in (select el_consum.cons_id from el_customer, el_consum, el_consum_item  where el_customer.cust_id=el_consum.cust_id and el_consum.cons_id = el_consum_item.cons_item_id and el_customer.cust_id = " +"'"+p_cust + "'"  +" and el_consum_item.cons_Time between  (NOW() - INTERVAL 5 MINUTE) and NOW() ) " +
	                      " group by el_consum.cons_id,el_consum.cons_Item ";
			
			//" and el_consum.cons_id = " + "'"+p_consID + "'" +
			
			Query query = em.createNativeQuery(sql);		
			return query.getResultList();
	
		}catch(Exception e2){
			e2.printStackTrace();
			System.out.println("**********exception findConsumerUnits***********");
			System.out.println(e2.getMessage());
			return null;
		}
	
	} }
	
