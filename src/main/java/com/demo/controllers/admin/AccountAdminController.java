package com.demo.controllers.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.RoleService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/account")
public class AccountAdminController {

	@Autowired
	private AccountSelectService accountSelectService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	// Template

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session) {
		modelMap.put("accounts", accountService.findAll());

		return "admin/account/index";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String create(ModelMap modelMap, HttpSession session) {

		return "admin/account/create";
	}

	@RequestMapping(value = { "detail" }, method = RequestMethod.GET)
	public String detail(ModelMap modelMap, HttpSession session) {

		return "admin/account/detail";
	}

	@RequestMapping(value = { "edit" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, HttpSession session) {

		return "admin/account/edit";
	}

	// Method
	@RequestMapping(value = { "add" }, method = RequestMethod.POST)
	public String Add(ModelMap modelMap, HttpSession session) {

		return "redirect:admin/account";
	}

	@RequestMapping(value = { "update" }, method = RequestMethod.PUT)
	public String update(ModelMap modelMap, HttpSession session) {

		return "redirect:/admin/account";
	}

	@RequestMapping(value = { "delete/{id}" }, method = RequestMethod.GET)
	public String delete(ModelMap modelMap, HttpSession session, @PathVariable("id") int id,
			RedirectAttributes redirectAttributes) {
		if (accountService.delete(id)) {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/admin/account/";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/admin/account/";
		}
	}
	@RequestMapping(value = { "block/{id}" }, method = RequestMethod.GET)
	public String block(ModelMap modelMap, HttpSession session, @PathVariable("id") int id,RedirectAttributes redirectAttributes) {
		Account account = accountService.find(id);
		account.setStatus(false);
		if (accountService.save(account)) {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/admin/account/";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/admin/account/";
		}
	}
	@RequestMapping(value = { "active/{id}" }, method = RequestMethod.GET)
	public String active(ModelMap modelMap, HttpSession session, @PathVariable("id") int id,RedirectAttributes redirectAttributes) {
		Account account = accountService.find(id);
		account.setStatus(true);
		if (accountService.save(account)) {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/admin/account/";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Success");
			return "redirect:/admin/account/";
		}
	}

}
