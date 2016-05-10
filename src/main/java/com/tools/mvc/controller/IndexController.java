package com.tools.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	/*
	 * Load the application
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String actorPage(Model model, HttpServletRequest request) {

		// user login status variable
		model.addAttribute("error_msg", null);
		return "login";
	}
}
