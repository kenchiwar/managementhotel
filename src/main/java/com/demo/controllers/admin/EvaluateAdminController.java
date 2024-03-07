package com.demo.controllers.admin;

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

import com.demo.entities.Evaluate;
import com.demo.services.AccountSelectService;
import com.demo.services.EvaluateService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("admin/evaluate")
public class EvaluateAdminController {
	@Autowired
	private RoomService roomService;

	@Autowired
	private EvaluateService evaluateService;

	@Autowired
	private AccountSelectService accountSelectService;
	//
	@RequestMapping(value= {"","/"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session) {
			evaluateService.findAll();
	return "admin/evaluate/index";
	}
	
	@RequestMapping(value= {"create"} ,method = RequestMethod.GET)
	public String create(ModelMap modelMap, HttpSession session) {
	modelMap.put("rooms", roomService.findAll());
	return "admin/evaluate/create";
	}
	@RequestMapping(value= {"hotel/{id}"} ,method = RequestMethod.GET)
	public String evaluate_hotel(@PathVariable("id") int id,ModelMap modelMap, Authentication authentication) {
		var account = accountSelectService.getAccountLogin(authentication);
		modelMap.put("evaluates", evaluateService.getEvaluates(id));
		return "admin/evaluate/hotel";
	}

	@RequestMapping(value= {"unactive/{id}"} ,method = RequestMethod.GET)
	public String unactive(@PathVariable("id") int id,RedirectAttributes redirectAttributes) {
			Evaluate evaluate = evaluateService.find(id);
			if (evaluate.isStatus()) {
				evaluate.setStatus(false);
			}else {
				evaluate.setStatus(true);
			}
			evaluateService.save(evaluate);
			// if (evaluateService.save(evaluate)) {
			// 	redirectAttributes.addFlashAttribute("msg", "Successfully!");
			// }else{
			// 	redirectAttributes.addFlashAttribute("msg", "Failed!");
			// }
			
		return "redirect:/admin/evaluate/hotel/" + evaluate.getHotel().getIdAccount();
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
