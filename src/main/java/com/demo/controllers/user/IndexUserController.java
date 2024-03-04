package com.demo.controllers.user;

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

import com.demo.helpers.SelectHelperHotel;
import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.HotelService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("/")
public class IndexUserController {
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
		SelectHelperHotel select = new SelectHelperHotel();
		select.setCity("01");
		var hotels = serviceHotel.hotelShowIndexs(select, null);
		hotels.forEach(x -> {
			
			if (x.getTotalrating() > 0) {
				x.setHaha(x.getHaha() * 100 / (x.getTotalrating() * 5));
			} else {
				x.setHaha(0l);
			}
		});
		
		
		//HCM
		select.setCity("79");
		var hotel = serviceHotel.hotelShowIndexs(select, null);
		hotels.forEach(x -> {
			if (x.getTotalrating() > 0) {
				x.setHaha(x.getHaha() * 100 / (x.getTotalrating() * 5));
			} else {
				x.setHaha(0l);
			}
		});
		hotels.addAll(hotel);
		//DA NANG
		select.setCity("48");
		 hotel = serviceHotel.hotelShowIndexs(select, null);
		hotel.forEach(x -> {
			if (x.getTotalrating() > 0) {
				x.setHaha(x.getHaha() * 100 / (x.getTotalrating() * 5));
			} else {
				x.setHaha(0l);
			}
		});
		hotels.addAll(hotel);
		hotels.forEach(x ->{
			if (x.getTotalrating() > 0) {
				x.setHaha(x.getHaha() * 100 / (x.getTotalrating() * 5));
			} else {
				x.setHaha(0l);
			}
		});
		modelMap.put("hotels", hotels);

		

		
	return "user/index";
	}
	
	
}
