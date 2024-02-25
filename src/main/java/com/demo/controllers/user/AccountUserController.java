package com.demo.controllers.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.services.AccountSelectService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("account")
public class AccountUserController {
	
	@Autowired
	private AccountSelectService accountSelectService;
	//Template
	
	@RequestMapping(value= {"","/"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session,Authentication authentication) {
	if(authentication != null) {
	modelMap.put("account", accountSelectService.getAccountLogin(authentication));
	}	
	return "user/account/index";
	}
	@RequestMapping(value= {"login"} ,method = RequestMethod.GET)
	public String login(ModelMap modelMap, HttpSession session,Authentication authentication) {
	
	if(authentication != null) {
		return "redirect:/account";
	}else {
	return "user/account/login";
	}
	}
	@RequestMapping(value= {"create"} ,method = RequestMethod.GET)
	public String create(ModelMap modelMap, HttpSession session) {
			
	return "user/account/create";
	}
	@RequestMapping(value= {"detail"} ,method = RequestMethod.GET)
	public String detail(ModelMap modelMap, HttpSession session) {
			
	return "user/account/detail";
	}
	@RequestMapping(value= {"edit"} ,method = RequestMethod.GET)
	public String edit(ModelMap modelMap, HttpSession session) {
			
	return "user/account/edit";
	}
		
	// Method 
	@RequestMapping(value= {"add"} ,method = RequestMethod.POST)
	public String Add(ModelMap modelMap, HttpSession session) {
			
	return "redirect:user/account";
	}
	
	@RequestMapping(value= {"update"} ,method = RequestMethod.PUT)
	public String update(ModelMap modelMap, HttpSession session) {
			
	return "redirect:user/account";
	}
	@RequestMapping(value= {"delete"} ,method = RequestMethod.DELETE)
	public String delete(ModelMap modelMap, HttpSession session) {
			
	return "redirect:user/account";
	}
	
}
