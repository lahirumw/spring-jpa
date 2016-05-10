package com.tools.mvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tools.mvc.domain.ElControl;
import com.tools.mvc.repo.ElConsumItemDao;
import com.tools.mvc.repo.ElControlDao;

@Controller
@RequestMapping(value="/cecb")
public class CecbController {
	
	@Autowired
	private ElControlDao elControlDao;
	@Autowired
	private ElConsumItemDao elConsumItemDao;
	
	private Date lastBillDate;
	private BigDecimal maxUnit;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String displayCecbPage(Model model, HttpServletRequest request){
		System.out.println("*****************  displayCecbPage");
		ElControl elControl = elControlDao.getControlData(1);
		if(elControl != null){
			lastBillDate = elControl.getLastBillDate();
			maxUnit = new BigDecimal(elControl.getAttrn1());
		}

		Calendar c = Calendar.getInstance();
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    //current month
	    model.addAttribute("curMonth", findMonth(c.get(Calendar.MONTH)));
	    model.addAttribute("totUnitCurMonth",elConsumItemDao.totalUnitsCurMonth());
	    
	    //daily units for current month
	    int i = 0;
	    ArrayList<Integer> perDayUnit = new ArrayList();
	    ArrayList<Date> curDate = new ArrayList();
	    while(c.getTime().compareTo(new Date()) < 0){
	    	i++;
	    	c.set(Calendar.DATE, i);
	    	System.out.println("*** " + c.getTime());
	    	perDayUnit.add(elConsumItemDao.totUnitPerDateCurrMon(c.getTime()));
	    	curDate.add(c.getTime());
	    }
	    model.addAttribute("perDayUnit", perDayUnit);
	    model.addAttribute("curDate", curDate);
	    
	    Calendar aCalendar1 = Calendar.getInstance();
		aCalendar1.add(Calendar.MONTH, -1);
		aCalendar1.set(Calendar.DATE, 1);
		Calendar aCalendar2 = Calendar.getInstance();
		aCalendar2.add(Calendar.MONTH, -1);
		aCalendar2.set(Calendar.DATE, 1);
		aCalendar2.set(Calendar.DATE,     aCalendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
		//previous month
		model.addAttribute("prewMonth", findMonth(aCalendar1.get(Calendar.MONTH)));
		model.addAttribute("totUnitPrewMonth", elConsumItemDao.totalUnitsPrewMonth());
		System.out.println(aCalendar1.getTime() + "  *************  " + aCalendar2.getTime());
		//daily units for previous month
		ArrayList<Integer> perDayUnitPrew = new ArrayList();
	    ArrayList<Date> prewDate = new ArrayList();
	    i = 0;
		while(aCalendar1.getTime().compareTo(aCalendar2.getTime()) < 0){
	    	i++;
	    	aCalendar1.set(Calendar.DATE, i);
	    	System.out.println(aCalendar1.getTime() + "  ^^^ " + aCalendar2.getTime());
	    	perDayUnitPrew.add(elConsumItemDao.totUnitPerDateCurrMon(aCalendar1.getTime()));
	    	prewDate.add(aCalendar1.getTime());
	    }
		model.addAttribute("perDayUnitPrew", perDayUnitPrew);
	    model.addAttribute("prewDate", prewDate);
		
		return "cecb";
	}
	
	
	private String findMonth(int mon){
		if(mon == 0){
			return "Janury";
		}else if(mon == 1){
			return "Februry";
		}else if(mon == 2){
			return "March";
		}else if(mon == 3){
			return "April";
		}else if(mon == 4){
			return "May";
		}else if(mon == 5){
			return "June";
		}else if(mon == 6){
			return "July";
		}else if(mon == 7){
			return "Augest";
		}else if(mon == 8){
			return "September";
		}else if(mon == 9){
			return "Octomber";
		}else if(mon == 10){
			return "November";
		}else if(mon == 11){
			return "December";
		}else{
			return "";
		}
	}

}
