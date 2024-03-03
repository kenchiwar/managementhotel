package com.demo.controllers.user;

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

import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.HotelService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("hotel")
public class HotelUserController {
	@Autowired
	private AccountSelectService selectAccountService;
	@Autowired
	AccountService serviceAccount;
	
	@Autowired
	private HotelService serviceHotel;
	@Autowired
	private RoomService serviceRoom;
	@RequestMapping(value= {"","/"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session) {
		modelMap.put("hotels", serviceHotel.hotelShowIndexs(null, null));	
	return "user/hotel/index";
	}
	
	@RequestMapping(value= "detail/{id}" ,method = RequestMethod.GET)
	public String Single(ModelMap modelMap, HttpSession session) {
			
	return "user/hotel/single";
	}
	
	
	
}
