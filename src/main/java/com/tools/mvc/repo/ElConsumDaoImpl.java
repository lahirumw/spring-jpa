package com.tools.mvc.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tools.mvc.domain.ElConsum;
import com.tools.mvc.domain.ElCustomer;

@Repository
@Transactional
public class ElConsumDaoImpl implements ElConsumDao {
	
	@Autowired
	private EntityManager em;

	@Override
	public List<ElConsum> getListConsumByCusId(int cusId) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ElConsum> criteria = cb.createQuery(ElConsum.class);
			Root<ElConsum> elConsum = criteria.from(ElConsum.class);
			
			criteria.select(elConsum).where(cb.equal(elConsum.get("custId"), cusId));
			return em.createQuery(criteria).getResultList();
						
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

}
