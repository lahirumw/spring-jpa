package com.tools.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tools.mvc.domain.ElCustomer;
import com.tools.mvc.repo.ElComptUserDao;
import com.tools.mvc.repo.ElCustomerDao;
import com.tools.mvc.repo.ElGroupDao;

@Controller
@RequestMapping(value = "/cust")
public class CustController {

	@Autowired
	private ElGroupDao elGroupDao;

	@Autowired
	private ElCustomerDao elCustomerDao;
	
	@Autowired
	private ElComptUserDao elComptUserDao;

	@RequestMapping(value = "/getCust", method = RequestMethod.GET)
	public String loadCustomer(Model model, HttpServletRequest request) {

		List<ElCustomer> list = elCustomerDao.getAll();
		model.addAttribute("customerList", list);
		System.out.println("list size is :" + list.size());

		Integer[] intArr1 = new Integer[] { 19, 25, 21, 26, 28, 31, 35, 31, 60, 31, 34, 32, 55, 40, 38, 29, 19, 25, 21, 26, 28, 31, 35, 31, 60, 31, 34, 32, 55, 40, 38, };

		Integer[] intArr2 = new Integer[] { 19, 25, 21, 26, 28, 31, 35, 31, 60, 31, 34, 32, 55, 40, 38, 29, 19, 25, 21, 26, 28, 31, 35, 31, 60, 31, 34, 32, 35, 33, 32, };

		model.addAttribute("intArr1", intArr1);
		model.addAttribute("intArr2", intArr2);

		model.addAttribute("challengeList", null);

		return "customer";
	}

	@RequestMapping(value = "/saveComp", method = RequestMethod.POST)
	public @ResponseBody
	String saveComp(Model model, HttpServletRequest request, @RequestParam(value = "compName", required = false) String name, @RequestParam(value = "compDesc", required = false) String desc,
			@RequestParam(value = "compUsrs", required = false) String users) {

		System.out.println(name);
		System.out.println(desc);
		System.out.println(users);

		int user_id = 1;
		String userList = "1,2,3";
		String desc1 = "group1";
		
		String[] groupMemberId = userList.split(",");
		String isSave = "N";
		System.out.println("^^^^^  " + groupMemberId.length);
		
		isSave = elGroupDao.saveGroup(user_id, desc1);
		int grpId = elGroupDao.selectMaxGrpId();
		System.out.println(grpId);
		
		for(int i=0; i<groupMemberId.length; i++){
			isSave = elComptUserDao.saveCompitisionData(Integer.parseInt(groupMemberId[i]), users, grpId);
		}
		return isSave;
	}

}
