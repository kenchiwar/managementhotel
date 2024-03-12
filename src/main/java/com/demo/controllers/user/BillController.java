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

	@RequestMapping(value= {"paypal/{id}"} ,method = RequestMethod.GET)
	public String home(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.put("billDetail", billDetailService.find(id));
        modelMap.put("payment", paymentService.find(1));
		return "user/hotel/paypal";
	}
    @RequestMapping(value= {"paypal"} ,method = RequestMethod.POST)
	public String payment(@ModelAttribute("billDetail") BillDetail billDetail) {
		try {
            var payment_ = paymentService.find(2);
            var total = billDetail.getTotal() / 24.695;
			Payment payment = service.createPayment(100.0, "USD", "paypal",
					"sale", "paypal bill", "http://localhost:8085/bill/" + CANCEL_URL,
					"http://localhost:8085/bill/");
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
