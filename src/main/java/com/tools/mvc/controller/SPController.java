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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tools.mvc.domain.ElControl;
import com.tools.mvc.domain.ElCustomer;
import com.tools.mvc.repo.DeviceDao;
import com.tools.mvc.repo.ElConsumItemDao;
import com.tools.mvc.repo.ElControlDao;
import com.tools.mvc.repo.ElCustomerDao;
import com.tools.mvc.repo.ElGroupDao;

@Controller
@RequestMapping(value="/client")
public class SPController {
	
	@Autowired
	private ElControlDao elControlDao;
	@Autowired
	private ElConsumItemDao elConsumItemDao;
	@Autowired
	private ElCustomerDao elCustomerDao;
	
	private String msg = "Your%20Current%20usage%20is%20";
	private BigDecimal maxUnit;
	
	@RequestMapping(method=RequestMethod.GET)
	public String loadClientPage(Model model, HttpServletRequest request){
		// daily units for current month
		ElControl elControl = elControlDao.getControlData(1);
		if(elControl != null){
			maxUnit = new BigDecimal(elControl.getAttrn1());
		}
		Calendar c = Calendar.getInstance();
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    
	    model.addAttribute("curMonth", findMonth(c.get(Calendar.MONTH))); //current month
	    model.addAttribute("totUnitCurMonth",elConsumItemDao.totalUnitsCurMonth()); // total unit for current month
	    
		int i = 0;
	    ArrayList<Integer> perDayUnit = new ArrayList();
	    ArrayList<Date> curDate = new ArrayList();
	    while(c.getTime().compareTo(new Date()) < 0){
	    	i++;
	    	c.set(Calendar.DATE, i);
	    	System.out.println("*** " + c.getTime());
	    	perDayUnit.add(elConsumItemDao.totUnitPerDateCurrMon(c.getTime()));
	    	//curDate.add(c.getTime());
	    }
	    
	    Integer[] intArr1 = new Integer[perDayUnit.size()];
	    
	    for(int n=0; n<perDayUnit.size(); n++){
	    	intArr1[n] = perDayUnit.get(n);
	    }
	    //*************************************************
	    
	    // daily units for previous month
	    Calendar aCalendar1 = Calendar.getInstance();
		aCalendar1.add(Calendar.MONTH, -1);
		aCalendar1.set(Calendar.DATE, 1);
		Calendar aCalendar2 = Calendar.getInstance();
		aCalendar2.add(Calendar.MONTH, -1);
		aCalendar2.set(Calendar.DATE, 1);
		aCalendar2.set(Calendar.DATE,     aCalendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		model.addAttribute("prevMonth", findMonth(aCalendar1.get(Calendar.MONTH))); //previous month
		model.addAttribute("totUnitPrewMonth", elConsumItemDao.totalUnitsPrewMonth()); // total units for previous months
		
	    ArrayList<Integer> perDayUnitPrew = new ArrayList();
	    //ArrayList<Date> prewDate = new ArrayList();
	    i = 0;
		while(aCalendar1.getTime().compareTo(aCalendar2.getTime()) < 0){
	    	i++;
	    	aCalendar1.set(Calendar.DATE, i);
	    	System.out.println(aCalendar1.getTime() + "  ^^^ " + aCalendar2.getTime());
	    	perDayUnitPrew.add(elConsumItemDao.totUnitPerDateCurrMon(aCalendar1.getTime()));
	    	//prewDate.add(aCalendar1.getTime());
	    }
		
		Integer[] intArr2 = new Integer[perDayUnitPrew.size()];
	    
	    for(int n=0; n<perDayUnitPrew.size(); n++){
	    	intArr2[n] = perDayUnitPrew.get(n);
	    }
		
		model.addAttribute("intArr1", intArr1);
		model.addAttribute("intArr2", intArr2);
		
		return "sprovider";
	}
	
	@RequestMapping(value = "/sendBill", method = RequestMethod.POST)
	public @ResponseBody
	String saveComp(Model model, HttpServletRequest request) {
		int bill = 0;
		List<ElCustomer>  elCustomer = elCustomerDao.getAll();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -20);
		Date twentyDaysAgo = cal.getTime();

		for (int i = 0; i < elCustomer.size(); i++) {
		try{
	
			System.out.println("elCustomer.get(i).getCustId() :"+elCustomer.get(i).getCustId());
		
	   int amount = elConsumItemDao.findToatalConsumItemUsage(elCustomer.get(i).getCustId(),twentyDaysAgo,new Date());
	   bill= amount*10;
	   
			String msg = "Your%20currnet%20monthly%20bill%20%20is%20%20RS.%20" + bill ;
			String link = "http://smeapps.mobitel.lk:8585/ESMS/esmsproxy2.php?u=esmsusr_test2&p=P@ssw0rd&a=TEST&m=" + msg + "&r=" + elCustomer.get(i).getTelNo();
			URL url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
	 
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	 
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}	 
			conn.disconnect();
	 
		 } catch (MalformedURLException e) {	 
				e.printStackTrace();	 
			 } catch (IOException e) { 
				e.printStackTrace();	 
			 }
		
		
		}
		System.out.println("success");
		return "send";
	}
	
	@RequestMapping(value = "/sendAlert", method = RequestMethod.POST)
	public @ResponseBody String sendAlert(Model model){
		
		ArrayList<String> phnAlertList = new ArrayList<String>();
		ArrayList<String> curUsageList = new ArrayList<String>();
		List<Object[]> totalConsLst = elConsumItemDao.findTotalConsumtionList();
		
		if(totalConsLst != null){
			for(int i=0; i<totalConsLst.size(); i++){
				if(((BigDecimal)totalConsLst.get(i)[2]).compareTo(maxUnit)>0){
					phnAlertList.add((String)totalConsLst.get(i)[1]);
					curUsageList.add((totalConsLst.get(i)[2]).toString());
					System.out.println((totalConsLst.get(i)[2]).toString() + " -------  " + (String)totalConsLst.get(i)[1] + " --- " + maxUnit + " --- " + maxUnit.add(new BigDecimal(20)));
				}
			}
		}
		
		if(phnAlertList != null){
			for(int i=0; i<phnAlertList.size(); i++){
				try {
					msg = msg + curUsageList.get(i) + ",%20Your%20next%20level%20is%20" + maxUnit.add(new BigDecimal(20));
					String link = "http://smeapps.mobitel.lk:8585/ESMS/esmsproxy2.php?u=esmsusr_test2&p=P@ssw0rd&a=TEST&m=" + msg + "&r=" + phnAlertList.get(i);
					URL url = new URL(link);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
			 
					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ conn.getResponseCode());
					}
			 
					BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
			 
					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}	 
					conn.disconnect();
			 
				  } catch (MalformedURLException e) {	 
					e.printStackTrace();	 
				  } catch (IOException e) { 
					e.printStackTrace();	 
				  }
				msg = "Your%20Current%20usage%20is%20";
			}
		}
		return "send";
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
