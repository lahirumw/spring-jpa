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



@Controller
public class DeviceController {
	
	@Autowired
	private DeviceDao deviceDao;

	
	/*@RequestMapping(method=RequestMethod.GET,value="/device")
	public String getDeviceDetails(Model model, HttpServletRequest request){*/
	
	@RequestMapping(value = "/device", method = RequestMethod.GET)
	public @ResponseBody
	String getDeviceDetails(Model model, HttpServletRequest request,
			@RequestParam(value = "customerid") String customerId,
			@RequestParam(value = "readings") String reading) {

		//String data = "3,90,1,25,2,30,1,40";
		//String custId ="1";
		String data = reading;
		String custId =customerId;
		String result="F";
		//String result = deviceDao.insertDeviceData(Integer.parseInt(custId),data);
		int length = 0;
		String[] deviceDtlArray = data.split(",");
					length = deviceDtlArray.length;
					
					if (length == 0) {

						result = "F";
					} else if (length > 0) {

						

							for (int i = 0; i < length; i++) {
		                       result = deviceDao.insertDeviceData1(Integer.parseInt(custId),Integer.parseInt(deviceDtlArray[i]) ,Integer.parseInt(deviceDtlArray[i+1] ));
		                       i = i+1;
							}
		
		
	}  
					model.addAttribute("name",result);
					return "device";			}
	
	@RequestMapping(method=RequestMethod.GET,value="/dashboard")
	public String getDeviceDetails(Model model, HttpServletRequest request){
	  List<Object[]> consUnit = deviceDao.findConsumerUnits("1","4",1);
	  //model.addAttribute("item",consUnit.get(0)[1]);
	  //model.addAttribute("usage",consUnit.get(0)[2]);
	  model.addAttribute("items",consUnit);
		return "dash";
	
} }

