package com.demo.controllers.user;


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
import com.demo.entities.Evaluate;
import com.demo.services.AccountSelectService;
import com.demo.services.BillDetailService;
import com.demo.services.BillService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;
import com.demo.services.PaymentService;
import com.demo.services.PaypalService;
import com.demo.services.RoomService;
import com.paypal.api.payments.Links;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.api.payments.Payment;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("bill")
public class BillController {
	
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
    private PaymentService paymentService;

	@Autowired
	private EvaluateService evaluateService;

	@Autowired
	PaypalService service;

	public static final String SUCCESS_URL = "success";
	public static final String CANCEL_URL = "cancel";
	
	@RequestMapping(value= {"", "/","detail/{id}"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, Authentication authentication, @PathVariable(value = "id", required = false) Integer id, Evaluate evaluate) {
		var account = accountSelectService.getAccountLogin(authentication);
		if(id == null){
			var billDetails = billDetailService.getBillDetails(account.getId());
			var billDetails_1 = billDetailService.getBillDetails_1(account.getId());
			modelMap.put("billDetails_1", billDetailService.getBillDetails_1(account.getId()));
			modelMap.put("billDetails_3_4", billDetailService.getBillDetails_3_4(account.getId()));
			modelMap.put("billDetails_5", billDetailService.getBillDetails_5(account.getId()));
			modelMap.put("billDetails", billDetails);
			modelMap.put("account", accountSelectService.getAccountLogin(authentication));
		}else {
			var billDetail = billDetailService.getBill_User(accountSelectService.getAccountLogin(authentication).getId(), id);
			modelMap.put("evaluate", evaluate);
			modelMap.put("billDetail", billDetail);
			modelMap.put("account", accountSelectService.getAccountLogin(authentication));
		}
		return "user/bill_evaluate/index";
	}

	@RequestMapping(value= {"cancel/{id}"} ,method = RequestMethod.GET)
	public String delete(ModelMap modelMap, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		var bill_detail = billDetailService.find(id);
		Bill bill = billService.find(bill_detail.getBill().getId());
		bill.setStatus("5");
		if(billService.save(bill)){
			redirectAttributes.addFlashAttribute("msg", "success");
		}else{
				redirectAttributes.addFlashAttribute("msg", "failed");
		}
		return "redirect:/bill";
	}
	
	// @RequestMapping(value= {"detail/{id}"} ,method = RequestMethod.GET)
	// public String Detail(@PathVariable("id") int id,ModelMap modelMap, Authentication authentication, Evaluate evaluate) {
	// 	var billDetail = billDetailService.getBill_User(accountSelectService.getAccountLogin(authentication).getId(), id);
	// 	modelMap.put("evaluate", evaluate);
	// 	modelMap.put("billDetail", billDetail);
	// 	return "user/bill_evaluate/detail";
	// }

	// @RequestMapping(value= {"create"} ,method = RequestMethod.GET)
	// public String create(ModelMap modelMap, HttpSession session, Authentication authentication) {
	// 	Bill bill = new Bill();
	// 	BillDetail billDetail = new BillDetail();
	// 	var account = accountSelectService.getAccountLogin(authentication);
	// 	modelMap.put("account", account);
	// 	modelMap.put("bill", bill);
	// 	modelMap.put("billDetail", billDetail);

	// 	return "admin/bill/create";
	// }

	// @RequestMapping(value = {"create"}, method = RequestMethod.POST)
	// public String addBill(@ModelAttribute("bill") Bill bill ,@ModelAttribute("billDetail") BillDetail billDetail,RedirectAttributes redirectAttributes, HttpSession session, Authentication authentication, ModelMap modelMap) {

	// 		var account = accountSelectService.getAccountLogin(authentication);
	// 		var hotel = hotelService.find(2);
	// 		var room = roomService.find(1);
	// 		bill.setStatus("1");
	// 		bill.setAccount(account);
			
	// 		if(billService.save(bill)){
	// 			var billDetails = new BillDetail();

	// 			// //dem so ngay da o
	// 			long dateBeforeInMs = bill.getCheckInUntil().getTime();
	// 			long dateAfterInMs = bill.getCheckOutUntil().getTime();
	// 			long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
	// 			long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

 	// 			Date date = bill.getCheckInFrom();
	// 			Date date_2 = bill.getCheckOutUntil();  // Đối tượng Date của bạn
    //     		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	// 			LocalDate localDate_2 = date_2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    //     		int year = localDate.getYear();
    //     		int month = localDate.getMonthValue();
    //     		int day = localDate.getDayOfMonth();

	// 			int year_2 = localDate_2.getYear();
    //     		int month_2 = localDate_2.getMonthValue();
    //     		int day_2 = localDate_2.getDayOfMonth();

	// 			LocalDateTime startDateTime = LocalDateTime.of(year, month, day, 14, 0, 0);
    //     		LocalDateTime endDateTime = LocalDateTime.of(year_2, month_2, day_2, 12, 0, 0);

    //     		Duration duration = Duration.between(startDateTime, endDateTime);
    //     		long hours = duration.toHours();

	// 			//create bill details khi tao bill
	// 			billDetails.setBill(bill);
	// 			billDetails.setNumberDay((double) daysDiff);
	// 			billDetails.setPrice(room.getPrice());
	// 			billDetails.setNumberHour((int) hours);
	// 			billDetails.setQuantity(billDetail.getQuantity());
	// 			//giá giảm
	// 			billDetails.setPriceDiscount(room.getPriceDiscount());
	// 			billDetails.setTotal(billDetails.getPriceDiscount());
	// 			billDetails.setRoom(room);
	// 			billDetails.getRoom().getId();
				
	// 			Double total;
	// 			Double percent_price = (double)0.1;
	// 			Double percent_price_day = (double)0.05;

	// 			total = billDetails.getPriceDiscount() * billDetails.getQuantity() * (1-percent_price) * billDetails.getNumberDay() * (1-percent_price_day);
	// 			billDetails.setTotal(total);
	// 			billDetailService.save(billDetails);

	// 			bill.setTotal(billDetails.getTotal());
	// 			billService.save(bill);
	// 			room.setRoomNow(room.getRoomNow() - billDetail.getQuantity());
	// 			roomService.save(room);
	// 			// modelMap.put("billPaypal", billDetailService.find(billDetails.getId()));

	// 			// modelMap.put("postUrl", environment.getProperty("paypal.posturl"));
	// 			// modelMap.put("returnurl", environment.getProperty("paypal.returnurl"));
	// 			// modelMap.put("business", environment.getProperty("paypal.business"));		
	// 			return "redirect:/bill/paypal/" + billDetails.getId();
	// 		}else{
	// 			redirectAttributes.addFlashAttribute("msg", "failed");
	// 		}
	// 			return "redirect:/admin/bill";
	// }

		// @GetMapping(value = {"paypal/{id}"})
	// public String home(ModelMap modelMap, @PathVariable("id") int id) {
    //     modelMap.put("billDetail", billDetailService.find(id));
    //     modelMap.put("payment", paymentService.findAll());
	// 	return "admin/bill/detail";
	// }
	@RequestMapping(value= {"paypal/{id}"} ,method = RequestMethod.GET)
	public String home(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.put("billDetail", billDetailService.find(id));
        modelMap.put("payment", paymentService.find(2));
		return "admin/bill/detail";
	}
    @RequestMapping(value= {"paypal"} ,method = RequestMethod.POST)
	public String payment(@ModelAttribute("billDetail") BillDetail billDetail) {
		try {
            var payment_ = paymentService.find(2);
            var total = billDetail.getTotal() / 24.695;
			Payment payment = service.createPayment(100.0, "USD", "paypal",
					"sale", "paypal bill", "http://localhost:8085/bill/" + CANCEL_URL,
					"http://localhost:8085/bill/" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@RequestMapping(value= {"cancel"} ,method = RequestMethod.GET)
	    public String cancelPay() {
	        return "cancel";
	    }

	@RequestMapping(value= {"success"} ,method = RequestMethod.GET)
	public String success() {
			
			return "success";
	}

}
