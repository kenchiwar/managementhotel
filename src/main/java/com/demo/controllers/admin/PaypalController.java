// package com.demo.controllers.admin;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.ModelMap;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.demo.configurations.PaypalPaymentIntent;
// import com.demo.configurations.PaypalPaymentMethod;
// import com.demo.entities.BillDetail;
// import com.demo.services.BillDetailService;
// import com.demo.services.PaymentService;
// import com.demo.services.PaypalService;
// import com.paypal.api.payments.Links;
// import com.paypal.api.payments.Payment;
// import com.paypal.base.rest.PayPalRESTException;

// @Controller
// @RequestMapping ("bill")
// public class PaypalController {

// 	@Autowired
// 	PaypalService service;
    
//     @Autowired
//     private BillDetailService billDetailService;

//     @Autowired
//     private PaymentService paymentService;

// 	public static final String SUCCESS_URL = "success";
// 	public static final String CANCEL_URL = "cancel";

// 	// @GetMapping(value = {"paypal/{id}"})
// 	// public String home(ModelMap modelMap, @PathVariable("id") int id) {
//     //     modelMap.put("billDetail", billDetailService.find(id));
//     //     modelMap.put("payment", paymentService.findAll());
// 	// 	return "admin/bill/detail";
// 	// }
// 	@RequestMapping(value= {"paypal/{id}"} ,method = RequestMethod.GET)
// 	public String home(ModelMap modelMap, @PathVariable("id") int id) {
//         modelMap.put("billDetail", billDetailService.find(id));
//         modelMap.put("payment", paymentService.find(2));
// 		return "admin/bill/detail";
// 	}
//     @RequestMapping(value= {"paypal"} ,method = RequestMethod.POST)
// 	public String payment(@ModelAttribute("billDetail") BillDetail billDetail) {
// 		try {
//             var payment_ = paymentService.find(2);
//             var total = billDetail.getTotal() / 24.695;
// 			Payment payment = service.createPayment(total, "USD", "paypal",
// 					"sale", "paypal bill", "http://localhost:8888/bill/" + CANCEL_URL,
// 					"http://localhost:8888/bill/" + SUCCESS_URL);
// 			for(Links link:payment.getLinks()) {
// 				if(link.getRel().equals("approval_url")) {
// 					return "redirect:"+link.getHref();
// 				}
// 			}
			
// 		} catch (PayPalRESTException e) {
		
// 			e.printStackTrace();
// 		}
// 		return "redirect:/";
// 	}
	
// 	@RequestMapping(value= {"cancel"} ,method = RequestMethod.GET)
// 	    public String cancelPay() {
// 	        return "cancel";
// 	    }

// 	@RequestMapping(value= {"success"} ,method = RequestMethod.GET)
// 	public String detail() {
			
// 			return "success";
// 	}
// }
