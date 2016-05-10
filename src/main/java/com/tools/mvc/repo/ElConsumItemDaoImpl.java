package com.tools.mvc.repo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tools.mvc.domain.ElConsum;
import com.tools.mvc.domain.ElConsumItem;
import com.tools.mvc.domain.ElCustomer;

@Transactional
@Repository
public class ElConsumItemDaoImpl implements ElConsumItemDao {

	@Autowired
	private EntityManager em;
	
	@Autowired
	private ElCustomerDao elCustomerDao;
	@Autowired
	private ElConsumDao elConsumDao;
	
	@Override
	public int findToatalConsumItemUsage(int custId, int NumOfDays) {
		int totalConsumption = 0;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -NumOfDays);
		ArrayList<Integer> consItemList = getConsItemId(custId);

		try {
			for(int i=0; i<consItemList.size(); i++){
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);
				Root<ElConsumItem> elConsumItem = criteria.from(ElConsumItem.class);
					
				Expression<Date> d1 = elConsumItem.get("consTime");
				Expression<Date> d2 = cb.literal(cal.getTime());
					
				criteria.select(elConsumItem.<Integer>get("itemUsage")).where(cb.equal(elConsumItem.get("itemId"), consItemList.get(i)), cb.greaterThanOrEqualTo(d1, d2));
				totalConsumption = totalConsumption + em.createQuery(criteria).getSingleResult();
				
			}
		return totalConsumption;	
		
		}catch(NullPointerException e1){
			e1.printStackTrace();
			System.out.println("No registered ElCustomer");
			return totalConsumption;
		}catch(Exception e2){
			e2.printStackTrace();
			System.out.println(e2.getMessage());
			return totalConsumption;
		}

	}

	@Override
	public int findToatalConsumItemUsage(int custId, Date fromDate, Date toDate) {
		
		int totalConsumption = 0;
		ArrayList<Integer> consItemList = getConsItemId(custId);
		
		try{
			for(int i=0; i<consItemList.size(); i++){
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);
				Root<ElConsumItem> elConsumItem = criteria.from(ElConsumItem.class);
				
				Expression<Date> d1 = elConsumItem.get("consTime");
				Expression<Date> d2 = cb.literal(fromDate);
				Expression<Date> d3 = cb.literal(toDate);
				
				criteria.select(elConsumItem.<Integer>get("itemUsage")).where(cb.equal(elConsumItem.get("itemId"), consItemList.get(i)), cb.between(d1, d2, d3));
				totalConsumption = totalConsumption + em.createQuery(criteria).getSingleResult();
			}
			return totalConsumption;
				
		}catch(NullPointerException e1){
			e1.printStackTrace();
			System.out.println("No registered ElCustomer");
			return totalConsumption;
		}catch(Exception e2){
			e2.printStackTrace();
			System.out.println(e2.getMessage());
			return totalConsumption;
		}
	}
	
	
	private ArrayList<Integer> getConsItemId(int custId){
		ArrayList<Integer> arr = new ArrayList();
		ElCustomer elCustomer = elCustomerDao.getElCustomerById(custId);
		
		List<ElConsum> list_elConsum = elConsumDao.getListConsumByCusId(custId);		
		
		List<ElConsumItem> list_elConsumItem = null;
		try{
			for(int i=0; i<list_elConsum.size(); i++){
				list_elConsumItem = findListItemByConsum(list_elConsum.get(i).getConsId());
				
				for(int j=0; j<list_elConsumItem.size(); j++){
					arr.add(list_elConsumItem.get(j).getItemId());
				}				
			}
			
		}catch(NullPointerException e1){
			e1.printStackTrace();
			System.out.println("No registered Consumes");
			return arr;
		}catch(Exception e2){
			e2.printStackTrace();
			System.out.println("Something wrong when find Consums Item");
			return arr;
		}
		return arr;
	}
	
	public List<Object[]> findMaximumConsumer(){
		System.out.println("*************  Inside the findMaximumConsumer  ************");
		try{
			String sql = "select el_customer.cust_id cust_id,sum(el_consum_item.item_usage) item_usage from "
					+ "el_customer, el_consum, el_consum_item where el_customer.cust_id=el_consum.cust_id and "
					+ "el_consum.cons_id = el_consum_item.cons_item_id group by el_customer.cust_id ORDER BY "
					+ "sum(el_consum_item.item_usage) desc LIMIT 1";
			Query query = em.createNativeQuery(sql);		
			return query.getResultList();
			
		}catch(NullPointerException e1){
			e1.printStackTrace();
			System.out.println(e1.getMessage());
			return null;
		}catch(Exception e2){
			e2.printStackTrace();
			System.out.println(e2.getMessage());
			return null;
		}
		
		
	}

	@Override
	public List<Object[]> findMinimumConsumer() {
		System.out.println("*************  Inside the findMinimumConsumer  ************");
		try{
			String sql = "select el_customer.cust_id cust_id,sum(el_consum_item.item_usage) item_usage from "
					+ "el_customer, el_consum, el_consum_item where el_customer.cust_id=el_consum.cust_id and "
					+ "el_consum.cons_id = el_consum_item.cons_item_id group by el_customer.cust_id ORDER BY "
					+ "sum(el_consum_item.item_usage) asc LIMIT 1";
			Query query = em.createNativeQuery(sql);		
			return query.getResultList();
			
			
		}catch(NullPointerException e1){
			e1.printStackTrace();
			System.out.println(e1.getMessage());
			return null;
		}catch(Exception e2){
			e2.printStackTrace();
			System.out.println(e2.getMessage());
			return null;
		}
	}

	@Override
	public List<ElConsumItem> findListItemByConsum(int consId) {
		System.out.println("*************  Inside the findListItemByConsum  ************");
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ElConsumItem> criteria = cb.createQuery(ElConsumItem.class);
			Root<ElConsumItem> elConsumItem = criteria.from(ElConsumItem.class);
			
			criteria.select(elConsumItem).where(cb.equal(elConsumItem.get("consItemId"), consId));
			return em.createQuery(criteria).getResultList();
						
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Object[]> findTotalConsumtionList() {
		try{
			String sql = "select el_customer.cust_id cust_id, el_customer.tel_no tel_no, sum(el_consum_item.item_usage) item_usage "
					+ "from el_customer, el_consum, el_consum_item where el_customer.cust_id=el_consum.cust_id and "
					+ "el_consum.cons_id = el_consum_item.cons_item_id group by el_customer.cust_id "
					+ "ORDER BY sum(el_consum_item.item_usage)";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public int totalUnitsCurMonth() {
		try{
			Calendar c = Calendar.getInstance();
		    c.set(Calendar.DAY_OF_MONTH, 1);
		    System.out.println(c.getTime());
			
		    CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> cq = cb.createTupleQuery();
			Root<ElConsumItem> root = cq.from(ElConsumItem.class);

			Expression<Integer> itemUsage = root.get("itemUsage");
			Expression<Integer> sumItemUsage = cb.sum(itemUsage);

			Expression<Date> d1 = root.get("consTime");
			Expression<Date> d2 = cb.literal(c.getTime());
			cq.multiselect(sumItemUsage.alias("sumItemUsage"));
			cq.where(cb.greaterThanOrEqualTo(d1, d2));
			
			TypedQuery<Tuple> tq = em.createQuery(cq);
			Object obj = null;
			for (Tuple t : tq.getResultList()) {
				obj = t.get("sumItemUsage");
			}
			return Integer.parseInt(obj.toString());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int totalUnitsPrewMonth() {
		try{
			Calendar aCalendar1 = Calendar.getInstance();
			aCalendar1.add(Calendar.MONTH, -1);
			aCalendar1.set(Calendar.DATE, 1);
			System.out.println(aCalendar1.getTime());
			Calendar aCalendar2 = Calendar.getInstance();
			aCalendar2.add(Calendar.MONTH, -1);
			aCalendar2.set(Calendar.DATE, 1);
			aCalendar2.set(Calendar.DATE,     aCalendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
			System.out.println(aCalendar2.getTime());
			
		    CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> cq = cb.createTupleQuery();
			Root<ElConsumItem> root = cq.from(ElConsumItem.class);

			Expression<Integer> itemUsage = root.get("itemUsage");
			Expression<Integer> sumItemUsage = cb.sum(itemUsage);

			Expression<Date> d1 = root.get("consTime");
			Expression<Date> d2 = cb.literal(aCalendar1.getTime());
			Expression<Date> d3 = cb.literal(aCalendar2.getTime());
			cq.multiselect(sumItemUsage.alias("sumItemUsage"));
			cq.where(cb.between(d1, d2, d3));
			
			TypedQuery<Tuple> tq = em.createQuery(cq);
			Object obj = null;
			for (Tuple t : tq.getResultList()) {
				obj = t.get("sumItemUsage");
			}
			return Integer.parseInt(obj.toString());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int totUnitPerDateCurrMon(Date date) {
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		System.out.println(date);
		d.add(Calendar.DATE, 1);
		System.out.println(date + "  &&&&&&&&&&&  " + d.getTime());
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> cq = cb.createTupleQuery();
			Root<ElConsumItem> root = cq.from(ElConsumItem.class);
	
			Expression<Integer> itemUsage = root.get("itemUsage");
			Expression<Integer> sumItemUsage = cb.sum(itemUsage);
	
			Expression<Date> d1 = root.get("consTime");
			Expression<Date> d2 = cb.literal(date);
			Expression<Date> d3 = cb.literal(d.getTime());
			cq.multiselect(sumItemUsage.alias("sumItemUsage"));
			cq.where(cb.between(d1, d2, d3));
			
			TypedQuery<Tuple> tq = em.createQuery(cq);
			Object obj = null;
			for (Tuple t : tq.getResultList()) {
				obj = t.get("sumItemUsage");
			}
			return Integer.parseInt(obj.toString());
		}catch(NullPointerException e0){
			e0.printStackTrace();
			System.out.println(e0.getMessage());
			return 0;
		}catch(NoResultException e1){
			e1.printStackTrace();
			System.out.println(e1.getMessage());
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}

	}

}
