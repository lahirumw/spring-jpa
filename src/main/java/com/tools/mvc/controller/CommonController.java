package com.tools.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tools.mvc.domain.ElConsum;
import com.tools.mvc.domain.ElConsumItem;
import com.tools.mvc.domain.ElCustomer;
import com.tools.mvc.repo.ElConsumDao;
import com.tools.mvc.repo.ElConsumItemDao;
import com.tools.mvc.repo.ElCustomerDao;

@Controller
@RequestMapping(value="/common")
public class CommonController {
	
	private String user_name;
	private int user_id;
	
	@Autowired
	private ElCustomerDao elCustomerDao;
	@Autowired
	private ElConsumItemDao elConsumItemDao;
	@Autowired
	private ElConsumDao elConsumDao;

	
	@RequestMapping(method=RequestMethod.GET)
	public String getCommonPageDetail(Model model, HttpServletRequest request){
		System.out.println("*************  Inside the CommonController  ************");
		user_id = 1;
		ElCustomer elCustomer = elCustomerDao.getElCustomerById(user_id);
		model.addAttribute("elCustomer" , elCustomer);
		//******* ElConsum list
		List<ElConsum> list_elConsum = elConsumDao.getListConsumByCusId(user_id);
		model.addAttribute("listElConsum" , list_elConsum);
		
		
		List<ElConsumItem> list_elConsumItem = null;
		try{
			for(int i=0; i<list_elConsum.size(); i++){
				list_elConsumItem = elConsumItemDao.findListItemByConsum(list_elConsum.get(i).getConsId());
				model.addAttribute("consumItem" + i, list_elConsumItem);
			}
			
		}catch(NullPointerException e1){
			e1.printStackTrace();
			System.out.println("No registered Consumes");
			list_elConsumItem = null;
		}catch(Exception e2){
			e2.printStackTrace();
			System.out.println("Something wrong when find Consums Item");
			list_elConsumItem = null;
		}
		
		List<Object[]> objMax = elConsumItemDao.findMaximumConsumer();
		List<Object[]> objMin = elConsumItemDao.findMinimumConsumer();
		
		model.addAttribute("maxConVal", objMax.get(0)[1]);
		model.addAttribute("maxConName", objMax.get(0)[0]);
		model.addAttribute("minConVal", objMin.get(0)[1]);
		model.addAttribute("minConName", objMin.get(0)[0]);
		
		return "common";
	}
	

}
