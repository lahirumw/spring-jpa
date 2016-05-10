package com.tools.mvc.repo;

import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tools.mvc.domain.ElComptUser;

@Repository
@Transactional
public class ElComptUserDaoImpl implements ElComptUserDao {
	
	@Autowired
	private EntityManager em;

	@Override
	public String saveCompitisionData(int user_id, String createdUser, int grpId) {
		try{
			ElComptUser elComptUser = new ElComptUser();
			elComptUser.setUserId(user_id);
			elComptUser.setGrpId(grpId);
			elComptUser.setCreatedUser(createdUser);
			elComptUser.setCreatedDate(new Date());
			elComptUser.setAttr1(1);
			elComptUser.setAttr2("2");
			elComptUser.setStatus("A");
			em.persist(elComptUser);
			return "Y";
			
		}catch(Exception e1){
			return "N";
		}
	}
	
	

}
