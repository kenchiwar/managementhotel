package com.demo.controllers.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.demo.entities.HotelShowIndex;
import com.demo.helpers.SelectHelperHotel;
import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.HotelService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("hotel")
public class HotelUserController {
	@Autowired
	private AccountSelectService selectAccountService;
	@Autowired
	AccountService serviceAccount;

	@Autowired
	private HotelService serviceHotel;
	@Autowired
	private RoomService serviceRoom;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String Index(
			@ModelAttribute(name = "search") SelectHelperHotel search
			,@RequestParam(name ="searchReset",required = false) boolean reset ,
			ModelMap modelMap, HttpSession session) {
		List<HotelShowIndex> hotels ;
		if(reset==true) {
			return "redirect:/hotel";
		}
		if((search !=null && search!= new SelectHelperHotel()) ) {
			hotels = serviceHotel.hotelShowIndexs(search, null);
			System.out.println(search.getCity());
			modelMap.put("search", search);
			
		}else {
			hotels = serviceHotel.hotelShowIndexs(null, null);
			
			hotels.forEach(x -> {
				if (x.getTotalrating() > 0) {
					x.setHaha(x.getHaha() * 100 / (x.getTotalrating() * 5));
				} else {
					x.setHaha(0l);
				}
			});
			modelMap.put("search", new SelectHelperHotel());
			
		}
		
		
		modelMap.put("hotels", hotels);
		return "user/hotel/index";
	}
	@RequestMapping(value = {"/booking/{id}"}, method = RequestMethod.GET)
	public String Booking(ModelMap modelMap, HttpSession session,@PathVariable("id") Integer id) {
		
		return "user/hotel/booking";
	}
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String Single(ModelMap modelMap, HttpSession session,@PathVariable("id") int id,@ModelAttribute(name = "search") SelectHelperHotel search
			,@RequestParam(name ="searchReset",required = false) boolean reset ) {
		var hotel = serviceHotel.find(id);
		modelMap.put("hotel", hotel);
		
		//hotels 
		SelectHelperHotel select = new SelectHelperHotel();
		List<HotelShowIndex> hotels ;
		if(reset==true) {
			return "redirect:/hotel";
		}
		if((search !=null && search!= new SelectHelperHotel()) ) {
			hotels = serviceHotel.hotelShowIndexs(search, null);
			System.out.println(search.getCity());
			modelMap.put("search", search);
			
		}else {
			hotels = serviceHotel.hotelShowIndexs(null, null);
			
			hotels.forEach(x -> {
				if (x.getTotalrating() > 0) {
					x.setHaha(x.getHaha() * 100 / (x.getTotalrating() * 5));
				} else {
					x.setHaha(0l);
				}
			});
			modelMap.put("search", new SelectHelperHotel());
			
		}
		hotels.forEach(x ->{
			if (x.getTotalrating() > 0) {
				x.setHaha(x.getHaha() * 100 / (x.getTotalrating() * 5));
			} else {
				x.setHaha(0l);
			}
		});
		
		modelMap.put("hotels", hotels);
		
		
		return "user/hotel/single";
		
	}

}
