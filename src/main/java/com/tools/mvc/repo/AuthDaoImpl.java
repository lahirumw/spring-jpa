package com.tools.mvc.repo;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tools.mvc.domain.ElConsum;
import com.tools.mvc.domain.ElCustomer;

@Transactional
@Repository
public class AuthDaoImpl implements AuthDao {

	@Autowired
	private EntityManager em;
	
	private String status;

	@Override
	public String getError() {
		return this.status;
	}

	@Override
	public boolean authenticate(String username, String password) {
		this.status = "your username is invalid!";
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ElCustomer> criteria = cb.createQuery(ElCustomer.class);
			Root<ElCustomer> elCustomer = criteria.from(ElCustomer.class);
			
			criteria.select(elCustomer).where(cb.equal(elCustomer.get("custName"), username));
			ElCustomer customer =  em.createQuery(criteria).getSingleResult();
			if(customer != null){
				return true;
			}else{
				return false;
			}
		}catch(NoResultException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false; 
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
		//return username.matches(password);
	}

	@Override
	public String getUserCategory(String username) {
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ElCustomer> criteria = cb.createQuery(ElCustomer.class);
			Root<ElCustomer> elCustomer = criteria.from(ElCustomer.class);
			
			criteria.select(elCustomer).where(cb.equal(elCustomer.get("custName"), username));
			ElCustomer customer =  em.createQuery(criteria).getSingleResult();
			if(customer != null){
				return customer.getCategory();
			}else{
				return null;
			}
		}catch(NoResultException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null; 
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

}
