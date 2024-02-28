package com.demo.controllers.admin;

import java.text.SimpleDateFormat;
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

import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("admin/evaluate")
public class EvaluateAdminController {
	@Autowired
	private RoomService roomService;
	//
	@RequestMapping(value= {"","/"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session) {
			
	return "admin/evaluate/index";
	}
	
	@RequestMapping(value= {"create"} ,method = RequestMethod.GET)
	public String create(ModelMap modelMap, HttpSession session) {
	modelMap.put("rooms", roomService.findAll());
	return "admin/evaluate/create";
	}
	@RequestMapping(value= {"detail"} ,method = RequestMethod.GET)
	public String detail(ModelMap modelMap, HttpSession session) {
			
	return "admin/evaluate/detail";
	}
	@RequestMapping(value= {"edit"} ,method = RequestMethod.GET)
	public String edit(ModelMap modelMap, HttpSession session) {
			
	return "admin/evaluate/edit";
	}
	
	///
	@RequestMapping(value= {"add"} ,method = RequestMethod.POST)
	public String Add(ModelMap modelMap, HttpSession session) {
			
	return "redirect:admin/evaluate/create";
	}
	@RequestMapping(value= {"update"} ,method = RequestMethod.PUT)
	public String update(ModelMap modelMap, HttpSession session) {
			
	return "redirect:/admin/evaluate";
	}
	@RequestMapping(value= {"delete"} ,method = RequestMethod.DELETE)
	public String delete(ModelMap modelMap, HttpSession session) {
			
	return "redirect:admin/evaluate";
	}
	
}
