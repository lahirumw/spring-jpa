package com.tools.mvc.repo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tools.mvc.domain.ElControl;


@Repository
@Transactional
public class ElControlDaoImpl implements ElControlDao {
	
	@Autowired
	private EntityManager em;

	@Override
	public ElControl getControlData(int contrId) {
		try{
			return em.find(ElControl.class, contrId);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	

}
