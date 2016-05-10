package com.tools.mvc.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class RuleDaoImpl implements RulesDao {
	

	@Autowired
	private EntityManager em;
	
	public List<Object[]> findRules() {
		
		try {
			String sql = "select rule_consumption_id,rule_option from el_rules where  now() between rule_from  and  rule_to";

			// " and el_consum.cons_id = " + "'"+p_consID + "'" +

			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e2) {
			e2.printStackTrace();
			System.out
					.println("**********exception findConsumerUnits***********");
			System.out.println(e2.getMessage());
			return null;
		}


} }
