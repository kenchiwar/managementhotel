package com.demo.controllers.admin;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Bill;
import com.demo.entities.BillDetail;
import com.demo.services.AccountSelectService;
import com.demo.services.BillDetailService;
import com.demo.services.BillService;
import com.demo.services.HotelService;
import com.demo.services.PaypalService;
import com.demo.services.RoomService;
import com.paypal.api.payments.Links;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.api.payments.Payment;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("admin/bill")
public class BillAdminController {
	
	@Autowired
	private BillService billService;

	@Autowired
	private AccountSelectService accountSelectService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private BillDetailService billDetailService;

	@Autowired
    private Environment environment;

	@Autowired
	private PaypalService paypalService;

	public static final String SUCCESS_URL = "success";
	public static final String CANCEL_URL = "cancel";
	

	//Đây là bill của thằng admin hotel
	@RequestMapping(value= {"","/"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, Authentication authentication) {
			var account = accountSelectService.getAccountLogin(authentication);
			modelMap.put("bill_1", billDetailService.getBillDetails_hotel(account.getId()));
				
			modelMap.put("bill_2", billDetailService.getBillDetails_hotel_2(account.getId()));
				
			modelMap.put("bill_3_4", billDetailService.getBillDetails_hotel_3_4(account.getId()));
			
				
			modelMap.put("bill_5", billDetailService.getBillDetails_hotel_5(account.getId()));
		return "admin/bill/index";
	}

	//Đây là bill của supper admin vô hotel
	@RequestMapping(value= {"hotel"} ,method = RequestMethod.GET)
	public String Hotel(ModelMap modelMap, Authentication authentication) {
		var account = accountSelectService.getAccountLogin(authentication);
			modelMap.put("bill_1", billDetailService.getBillDetails_hotel(account.getId()));
			modelMap.put("bill_2", billDetailService.getBillDetails_hotel_2(account.getId()));
			modelMap.put("bill_3_4", billDetailService.getBillDetails_hotel_3_4(account.getId()));
			
			modelMap.put("bill_5", billDetailService.getBillDetails_hotel_5(account.getId()));
		return "admin/bill/index";
	}
	
	@RequestMapping(value= {"hotel/{id}"} ,method = RequestMethod.GET)
	public String Hotel(ModelMap modelMap, Authentication authentication, @PathVariable("id") int id) {
		var account = accountSelectService.getAccountLogin(authentication);
			modelMap.put("bill_1", billDetailService.getBillDetails_hotel(id));
			modelMap.put("bill_2", billDetailService.getBillDetails_hotel_2(id));
			modelMap.put("bill_3_4", billDetailService.getBillDetails_hotel_3_4(id));
			
			modelMap.put("bill_5", billDetailService.getBillDetails_hotel_5(id));
		return "admin/bill/index";
	}

	
	@RequestMapping(value= {"create"} ,method = RequestMethod.GET)
	public String create(ModelMap modelMap, HttpSession session, Authentication authentication) {
		Bill bill = new Bill();
		BillDetail billDetail = new BillDetail();
		var account = accountSelectService.getAccountLogin(authentication);
		modelMap.put("account", account);
		modelMap.put("bill", bill);
		modelMap.put("billDetail", billDetail);

		return "admin/bill/create";
	}


	@RequestMapping(value= {"detail/{id}"} ,method = RequestMethod.GET)
	public String detail(ModelMap modelMap, @PathVariable("id") int id) {
			modelMap.put("billPaypal", billDetailService.find(id));

			return "admin/bill/detail";
	}
	
}
