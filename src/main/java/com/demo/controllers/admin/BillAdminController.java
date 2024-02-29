package com.demo.controllers.admin;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Bill;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountSelectService;
import com.demo.services.BillService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("admin/bill")
public class BillAdminController {
	
	@Autowired
	private BillService billService;

	@Autowired
	private AccountSelectService accountSelectService;
	
	@RequestMapping(value= {"","/"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session) {
			
	return "admin/bill/index";
	}
	
	@RequestMapping(value= {"create"} ,method = RequestMethod.GET)
	public String create(ModelMap modelMap, HttpSession session, Authentication authentication) {
		Bill bill = new Bill();
		var account = accountSelectService.getAccountLogin(authentication);
		System.out.println("aaaaaaaaaaaaa: " + account.getEmail());
		session.setAttribute("id", account.getId());
		modelMap.put("bill", bill);
		return "admin/bill/create";
	}

	@RequestMapping(value = {"create"}, method = RequestMethod.POST)
	public String addBill(@ModelAttribute("bill") Bill bill ,RedirectAttributes redirectAttributes, HttpSession session){
			Date now = new Date();
			bill.setAccount(new Account());
			if(billService.save(bill)){
			redirectAttributes.addFlashAttribute("msg", "success");
			}else{
			redirectAttributes.addFlashAttribute("msg", "failed");
			}
		return "redirect:/admin/bill/create";
	}

	@RequestMapping(value= {"detail"} ,method = RequestMethod.GET)
	public String detail(ModelMap modelMap, HttpSession session) {
			
	return "admin/bill/detail";
	}
	@RequestMapping(value= {"edit"} ,method = RequestMethod.GET)
	public String edit(ModelMap modelMap, HttpSession session) {
			
	return "admin/bill/edit";
	}
		
	// Method 
	@RequestMapping(value= {"add"} ,method = RequestMethod.POST)
	public String Add(ModelMap modelMap, HttpSession session) {
			
	return "redirect:admin/bill";
	}
	
	@RequestMapping(value= {"update"} ,method = RequestMethod.PUT)
	public String update(ModelMap modelMap, HttpSession session) {
			
	return "redirect:/admin/bill";
	}
	@RequestMapping(value= {"delete"} ,method = RequestMethod.DELETE)
	public String delete(ModelMap modelMap, HttpSession session) {
			
	return "redirect:admin/bill";
	}
	
}
