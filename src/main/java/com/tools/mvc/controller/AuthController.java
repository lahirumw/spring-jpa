package com.tools.mvc.controller;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tools.mvc.repo.AuthDao;

@Controller
public class AuthController {

	@Autowired
	private AuthDao authDao;

	@Autowired
	private CustController custController;

	@Autowired
	private SPController spController;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String actorPage(Model model, HttpServletRequest request, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

		if (!authDao.authenticate(username, password)) {
			model.addAttribute("error_msg", authDao.getError());
			return "login";
		}

		// ********** GLOBAL SESSION VARIABLES ************/
		HttpSession sess = request.getSession();
		sess.setAttribute("user_image", "lahiruB");
		sess.setAttribute("user_id", 1);
		sess.setAttribute("user_name", "Lahiru Bandara");
		sess.setAttribute("user_title", "Administrative Secretaty");

		// get the logged in user type
		if ((authDao.getUserCategory(username)).equals("C")) {
			return spController.loadClientPage(model, request);
		}

		return custController.loadCustomer(model, request);
	}
}
