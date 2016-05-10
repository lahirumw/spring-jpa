package com.tools.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tools.mvc.repo.DeviceDao;
import com.tools.mvc.repo.RulesDao;

@Controller
public class RuleController {

		@Autowired
		private RulesDao rulesDao;
		
		@RequestMapping(method=RequestMethod.GET,value="/rules")
		public @ResponseBody
		 String getRulesDetails(Model model, HttpServletRequest request,
				  @RequestParam(value = "code") String code){
		  
		  List<Object[]>  rules = rulesDao.findRules();
		  
		  String ini=code;
		 
		  String reuslt= null;
		  char[] array=new  char[ini.length()];
		 
		  for(int count=0;count<ini.length();count++){
			  
		         array[count] = ini.charAt(count);
			 // array[count] = ini.substring(count, count+1);
		  
		  }
		  
		  
		  String  bb;
		  int position;
		  for (int i = 0; i < rules.size(); i++) {
			   bb = (String) rules.get(i)[1];
			   position =(Integer) rules.get(i)[0];
				char a = bb.charAt(0);
			  array[position]=a;
		  }
		  
		 
		  for(int count1=0;count1<array.length;count1++){
			  
			  reuslt = reuslt+array[count1];
		  
		  }
		 
		
		
		 // model.addAttribute("items",rules);
			return reuslt.replace("null","");
		
	}
		
		
}
