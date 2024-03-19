package com.demo.controllers.user;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Bill;
import com.demo.entities.BillDetail;
import com.demo.entities.Evaluate;
import com.demo.entities.Item;
import com.demo.entities.Order;
import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.BillDetailService;
import com.demo.services.BillService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;
import com.demo.services.MailService;
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
	private AccountService accountService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private BillDetailService billDetailService;

	@Autowired
    private Environment environment;

	@Autowired
	private MailService mailService;

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
			// var billDetails_1 = billDetailService.getBillDetails_1(account.getId() );
			modelMap.put("billDetails_1", billDetailService.getBills(account.getId()));
			// modelMap.put("billDetails_1", billDetailService.getBills_2(account.getId()));
			modelMap.put("billDetails_3_4", billDetailService.getBills_3_4(account.getId()));
			modelMap.put("billDetails_5", billDetailService.getBills_5(account.getId()));
			modelMap.put("billDetails", billDetails);
			modelMap.put("account", accountSelectService.getAccountLogin(authentication));
		}else {
			var billDetail = billDetailService.getBill_User(account.getId(), id);
			var evaluate_bill = evaluateService.getEvaluate_bill(billDetail.getBill().getId(), account.getId());
			modelMap.put("evaluate", evaluate);
			modelMap.put("eva", evaluate_bill);
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
			var room = roomService.find(bill_detail.getRoom().getId());
			room.setRoomNow(bill_detail.getQuantity() + room.getRoomNow());
			roomService.save(room);
			redirectAttributes.addFlashAttribute("msg", "success");
		}else{
				redirectAttributes.addFlashAttribute("msg", "failed");
		}
		return "redirect:/bill";
	}

	@RequestMapping(value= {"paypal"} ,method = RequestMethod.GET)
	public String paypal(ModelMap modelMap, HttpSession session) {
		
        // modelMap.put("billDetail", billDetailService.find(id));
		if (session.getAttribute("cart") != null) {
			Item item = (Item) session.getAttribute("cart");
			double usd = item.getBillDetail().getTotal() / 24695;
			DecimalFormat df = new DecimalFormat("#.##");
			String usdFormatted = df.format(usd);
			modelMap.put("usd", usdFormatted);
			modelMap.put("cart", item);
		}else {
			Item cart = null;
			modelMap.put("cart", cart);
		}
		
		return "user/hotel/paypal";
	}

	@RequestMapping(value= {"paypal/{id}"} ,method = RequestMethod.GET)
	public String home(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.put("billDetail", billDetailService.find(id));
		return "user/hotel/paypal";
	}
    @RequestMapping(value= {"paypal"} ,method = RequestMethod.POST)
	public String payment(@ModelAttribute("billDetail") BillDetail billDetail, @ModelAttribute("order") Order order, Authentication authentication, HttpSession session) throws Exception {
		var account = accountSelectService.getAccountLogin(authentication);
		if ((Item) session.getAttribute("cart") != null) {
			Item item = (Item) session.getAttribute("cart");
			
			var bill = item.getBillDetail().getBill();
			if (billService.save(bill)) {
				var bill_Detail  = item.getBillDetail();
				billDetailService.save(bill_Detail);
				var room_ = item.getRoom();
				roomService.save(room_);

				try {
					//            var payment_ = paymentService.find(2);
								var total = billDetail.getTotal() / 24695;
								Payment payment = service.createPayment(total, "USD", "paypal",
										"sale", "paypal bill", "http://localhost:8085/bill/" + CANCEL_URL,
										"http://localhost:8085/bill/success");
								for(Links link:payment.getLinks()) {
									if(link.getRel().equals("approval_url")) {
										
										return "redirect:"+link.getHref();
									}
								}
							
							} catch (PayPalRESTException e) {
							
								e.printStackTrace();
							}
			}
		}else {
			Item cart = null;
			return "redirect:/";
		}

		return "redirect:/";

		// var bill_detail = billDetailService.find(billDetail.getId());
		// var bill = billService.find(bill_detail.getBill().getId());

		
		
	}
	
	@RequestMapping(value= {"cancel"} ,method = RequestMethod.GET)
	    public String cancelPay() {
	        return "cancel";
	    }

	public void sendMail(BillDetail bill_Detail){
		var account = accountService.find(bill_Detail.getBill().getAccount().getId());
		var business = accountService.find(bill_Detail.getRoom().getHotel().getIdAccount());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		var dateFrom = simpleDateFormat.format(bill_Detail.getBill().getCheckInUntil());
		var dateTo = simpleDateFormat.format(bill_Detail.getBill().getCheckOutFrom());
		String content = "";
					content += "<div style='margin: auto; background: rgba(1,148,243,1); max-width: 500px; border-radius: 20px; overflow: hidden; padding: 20px 40px;color: #fff;'>";
					content += "<h5 style='text-align: center; font-size: 30px; margin: 0;'>Wellcome To " + bill_Detail.getRoom().getHotel().getName() + "</h5> ";
					content += "<h6 style='text-align: center; font-size: 20px; margin: 0;'>Information Bill</h6> ";
					content += "<div>";
					content += "<p class='hotel-name' style='margin: 0; font-size: 18px;'>Hotel "+bill_Detail.getRoom().getHotel().getName()+"</p>";
					content += "<p class='room-name' style='margin: 0; font-size: 18px;'>Room "+bill_Detail.getRoom().getName()+"</p>";
					content += "<p class='date' style='margin: 0; font-size: 16px;'>From "+dateFrom+" To "+ dateTo+ "</p>";
					content += "<p class='main-guest' style='margin: 0; font-size: 16px;'>Main Guest: "+bill_Detail.getBill().getName()+ "</p>";
					content += "<p class='price-total' style='margin: 0; font-size: 16px;'>Price Total: "+bill_Detail.getBill().getTotal()+ "</p>";
					content += "<h5 class='thanks' style='text-align: center; font-size: 20px; margin: 20px 0;'>Thanks!</h5>";
					content += "</div>";
					content += "</div>";
										String email = environment.getProperty("spring.mail.username");
										mailService.sendMail(email,account.getEmail(),bill_Detail.getRoom().getHotel().getName(), content);
										mailService.sendMail(email,business.getEmail(),bill_Detail.getRoom().getHotel().getName(), content);
	}

	@RequestMapping(value= {"success"} ,method = RequestMethod.GET)
	 public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpSession session) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
					Item item = (Item) session.getAttribute("cart");
					sendMail(item.getBillDetail());
	            if (payment.getState().equals("approved")) {
					session.removeAttribute("cart");
	                return "redirect:/bill";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/bill";
	    }


}
