package com.tools.mvc.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tools.mvc.domain.ElCustomer;

@Repository
@Transactional
public class ElCustomerDaoImpl implements ElCustomerDao {
	
	@Autowired
	private EntityManager em;

	@Override
	public List<ElCustomer> getElCustomerInfomation(String custName) {
		System.out.println("*************  Inside the getElCustomerInfomation  ************");
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ElCustomer> criteria = cb.createQuery(ElCustomer.class);
			Root<ElCustomer> elCustomer = criteria.from(ElCustomer.class);
			
			criteria.select(elCustomer).where(cb.equal(elCustomer.get("custName"), custName));
			return em.createQuery(criteria).getResultList();
						
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	@Override
	public ElCustomer getElCustomerById(int custId) {
		System.out.println("*************  Inside the getElCustomerById  ************");
		try{
			return em.find(ElCustomer.class, custId);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	public List<String> getRandomCustomerSearch(String param){
		System.out.println("*************  Inside the getRandomCustomerSearch  ************");
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<String> criteria = cb.createQuery(String.class);
			Root<ElCustomer> elCustomer = criteria.from(ElCustomer.class);
			
			Predicate p1 = cb.equal(elCustomer.get("custName"), param);
			Predicate p2 = cb.equal(elCustomer.get("add1"), param);
			Predicate p3 = cb.equal(elCustomer.get("add2"), param);
			Predicate p4 = cb.equal(elCustomer.get("add3"), param);
			Predicate p12 = cb.or(p1, p2);
			Predicate p34 = cb.or(p3, p4);
			Predicate pred = cb.or(p12, p34);
			
			criteria.select(elCustomer.<String>get("custName")).where(pred);
			return em.createQuery(criteria).getResultList();
						
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	@Override
	public List<ElCustomer> getAll() {

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ElCustomer> criteria = cb
					.createQuery(ElCustomer.class);
			Root<ElCustomer> elCustomer = criteria.from(ElCustomer.class);
			criteria.select(elCustomer);
			return em.createQuery(criteria).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

}
