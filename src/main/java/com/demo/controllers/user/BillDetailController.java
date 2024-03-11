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

import com.demo.entities.Bill;
import com.demo.entities.BillDetail;
import com.demo.services.BillDetailService;
import com.demo.services.BillService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping ("billdetail")
public class BillDetailController {
	
	@Autowired
	private BillDetailService billDetailService;
	@Autowired
	private BillService billService;
	@Autowired RoomService roomServices;
	
	@RequestMapping(value= {"/{id}"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, @PathVariable("id") int id) {
		
		var billDetail = billDetailService.find(id);
		modelMap.put("checkBill", billDetail);
		switch (billDetail.getBill().getStatus()) {
			case "1":
				modelMap.put("billDetails", billDetailService.getBillDetails_1(billDetail.getBill().getAccount().getId()));
				break;
			case "2":
				modelMap.put("billDetails", billDetailService.getBillDetails_2(billDetail.getBill().getAccount().getId()));
				break;
			case "3":
				modelMap.put("billDetails", billDetailService.getBillDetails_3_4(billDetail.getBill().getAccount().getId()));
				break;
			case "4":
				modelMap.put("billDetails", billDetailService.getBillDetails_3_4(billDetail.getBill().getAccount().getId()));
				break;
			default:
				modelMap.put("billDetails", billDetailService.getBillDetails_5(billDetail.getBill().getAccount().getId()));
				break;
		}
		return "admin/billdetail/index";
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
		return "redirect:/admin/bill";
	}
	
}
