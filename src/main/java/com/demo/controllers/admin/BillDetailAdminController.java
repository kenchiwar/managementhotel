package com.demo.controllers.admin;

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
@RequestMapping ("admin/billdetail")
public class BillDetailAdminController {
	
	@Autowired
	private BillDetailService billDetailService;
	@Autowired
	private BillService billService;
	@Autowired RoomService roomServices;
	
	@RequestMapping(value= {"/{id}"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, @PathVariable("id") int id) {
		// BillDetail billDetail = new BillDetail();
		// var rollBill = billDetailService.getBillDetails(id);
		// for (BillDetail billDetail_ : rollBill) {
		// 	billDetail = billDetailService.find(billDetail_.getId());
		// }
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
	
	@RequestMapping(value= {"create"} ,method = RequestMethod.GET)
	public String create(ModelMap modelMap, HttpSession session) {
			
	return "admin/billdetail/create";
	}
	@RequestMapping(value= {"detail"} ,method = RequestMethod.GET)
	public String detail(ModelMap modelMap, HttpSession session) {
			
	return "admin/billdetail/detail";
	}
	@RequestMapping(value= {"edit/{id}"} ,method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		var bill_detail = billDetailService.find(id);
		Bill bill = billService.find(bill_detail.getBill().getId());
		Bill bill_ = new Bill();
		switch (bill.getStatus()) {
			case "1":
				bill.setStatus("2");
				break;
			case "2":
				bill.setStatus("3");
				var room = roomServices.find(bill_detail.getRoom().getId());
				room.setRoomNow(bill_detail.getRoom().getRoomNow() + bill_detail.getQuantity());
				roomServices.save(room);
				break;
			default:
				break;
		}
		if(billService.save(bill)){
			redirectAttributes.addFlashAttribute("msg", "success");
		}else{
				redirectAttributes.addFlashAttribute("msg", "failed");
		}
		return "redirect:/admin/bill";
	}

	@RequestMapping(value= {"edit_reason/{id}"} ,method = RequestMethod.GET)
	public String edit_reason(@PathVariable("id") int id, RedirectAttributes redirectAttributes, ModelMap modelMap, BillDetail billDetail) {
		var bill_detail = billDetailService.find(id);
		Bill bill_ = new Bill();
		modelMap.put("billDetail", bill_detail);
		modelMap.put("billDetail_form", billDetail);
		return "admin/billdetail/edit";
	}
		
	// Method 
	@RequestMapping(value= {"add"} ,method = RequestMethod.POST)
	public String Add(ModelMap modelMap, HttpSession session) {
			
	return "redirect:admin/billdetail";
	}
	
	@RequestMapping(value= {"update"} ,method = RequestMethod.POST)
	public String update(@ModelAttribute("billDetail") BillDetail billDetail, HttpSession session) {
		var bill_detail = billDetailService.find(billDetail.getId());
		bill_detail.setReasonDiscount(billDetail.getReasonDiscount());
		
		billDetailService.save(bill_detail);
	return "redirect:/admin/bill";
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
