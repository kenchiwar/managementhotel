package com.demo.controllers.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Role;
import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.RoleService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class AccountUserController {

	@Autowired
	private AccountSelectService accountSelectService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	// Template

	@RequestMapping(value= {"","/"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session,Authentication authentication) {
		
		if(authentication == null) {
			
			return "redirect:/account/login";
		}else {
			
			if(authentication != null) {
			
			modelMap.put("account", accountSelectService.getAccountLogin(authentication));
			}	
			return "user/account/index";
		}
	}
	@RequestMapping(value= {"login"} ,method = RequestMethod.GET)
	public String login(ModelMap modelMap, HttpSession session,Authentication authentication) {
	
	if(authentication != null) {
		return "redirect:/account";
	}else {
	return "user/account/login";
	}
	}

	@RequestMapping(value = { "register" }, method = RequestMethod.GET)
	public String create(ModelMap modelMap, HttpSession session) {
		Account account = new Account();

		modelMap.put("account", account);

		return "user/account/register";
	}
	@RequestMapping(value = { "registerbusiness" }, method = RequestMethod.GET)
	public String registerbussiness(ModelMap modelMap, HttpSession session) {
		Account account = new Account();

		modelMap.put("account", account);

		return "user/account/registerbusiness";
	}

	@RequestMapping(value = { "detail" }, method = RequestMethod.GET)
	public String detail(ModelMap modelMap, HttpSession session) {

		return "user/account/detail";
	}

	@RequestMapping(value = { "editpassword" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, HttpSession session,Authentication authentication) {
		modelMap.put("account", accountSelectService.getAccountLogin(authentication));
		return "user/account/rpassword";
	}

	// Method
	@RequestMapping(value = { "add" }, method = RequestMethod.POST)
	public String add(ModelMap modelMap, HttpSession session, @ModelAttribute("account") Account account,
		RedirectAttributes redirectAttributes) {
		Role role = new Role();
		role = roleService.find(4);
		account.setStatus(true);
		account.setActive("Online");
		account.setImage("user.png");
		account.setRole(role);
		account.setPassword(encoder.encode(account.getPassword()));

		if (accountService.save(account)) {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/account/login";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/account/reister";
		}

	}

	@RequestMapping(value = { "addbusiness" }, method = RequestMethod.POST)
	public String addbussines(ModelMap modelMap, HttpSession session, @ModelAttribute("account") Account account,
		RedirectAttributes redirectAttributes) {
		Role role = new Role();
		role = roleService.find(3);
		account.setStatus(true);
		account.setActive("Online");
		account.setImage("user.png");
		account.setRole(role);
		account.setPassword(encoder.encode(account.getPassword()));

		if (accountService.save(account)) {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/account/login";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed");
			return "redirect:/account/reisterbussiness";
		}

	}
	@RequestMapping(value = { "update" }, method = RequestMethod.POST)
	public String update(ModelMap modelMap, HttpSession session,@ModelAttribute("account")Account account,Authentication authentication,RedirectAttributes redirectAttributes) {
		Account accountU= accountSelectService.getAccountLogin(authentication);
		accountU.setFirstName(account.getFirstName());
		accountU.setLastName(account.getLastName());
		accountU.setPhone(account.getPhone());
		if (accountService.save(accountU)) {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/account/";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Error");
			return "redirect:/account/";
		}
	}
	@RequestMapping(value = { "updatepass" }, method = RequestMethod.POST)
	public String updatepass(ModelMap modelMap, HttpSession session,@RequestParam("password") String password ,Authentication authentication,RedirectAttributes redirectAttributes) {
		Account accountU= accountSelectService.getAccountLogin(authentication);
		accountU.setPassword(encoder.encode(password));
		if (accountService.save(accountU)) {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/account/logout";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Error");
			return "redirect:/account/";
		}
	}

	@RequestMapping(value = { "delete" }, method = RequestMethod.DELETE)
	public String delete(ModelMap modelMap, HttpSession session) {

		return "redirect:user/account";
	}

}
