package com.tools.mvc.repo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tools.mvc.domain.ElCustomer;
import com.tools.mvc.domain.ElGroup;

@Transactional
@Repository
public class ElGroupDaoImpl implements ElGroupDao {

	@Autowired
	private EntityManager em;
	
	@Autowired
	private ElCustomerDao elCustomerDao;
	
	@Override
	public String saveGroup(int user_id, String desc) {
		try{
			ElCustomer customer = elCustomerDao.getElCustomerById(user_id);
			if(customer != null){
				ElGroup elGroup = new ElGroup();
				elGroup.setAttr1(1);
				elGroup.setAttr2("A");
				elGroup.setGrpName(desc);
				elGroup.setGrpLead(user_id);
				elGroup.setCreatedUser(customer.getCustName());
				elGroup.setCreatedDate(new Date());
				em.persist(elGroup);
				return "Y";
			}else{
				return "N";
			}
			
		}catch(Exception e){
			return "N";
		}
	}

	@Override
	public int selectMaxGrpId() {
		try{
			String sql = "select max(group_id) from el_group";
			Query query = em.createNativeQuery(sql);
			Object obj = query.getSingleResult();
			return Integer.parseInt(obj.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

}
