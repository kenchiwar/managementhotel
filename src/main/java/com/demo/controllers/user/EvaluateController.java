package com.demo.controllers.user;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
import org.springframework.security.core.Authentication;

import com.demo.entities.Bill;
import com.demo.entities.BillDetail;
import com.demo.entities.Evaluate;
import com.demo.services.AccountSelectService;
import com.demo.services.BillDetailService;
import com.demo.services.BillService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping ("evaluate")
public class EvaluateController {
	@Autowired
	private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private AccountSelectService accountSelectService;

	@Autowired
	private EvaluateService evaluateService;

    @Autowired
    private BillService billService;
	//
	@RequestMapping(value= {"", "/"} ,method = RequestMethod.GET)
	public String Index(ModelMap modelMap, Authentication authentication) {
		var account = accountSelectService.getAccountLogin(authentication);
		var evaluates = evaluateService.getEvaluates_user(account.getId());
		var billDetails = billDetailService.getBillDetails_3_4(account.getId());
		modelMap.put("evaluates", billDetails);
		modelMap.put("account", accountSelectService.getAccountLogin(authentication));
		return "user/bill_evaluate/index";
	}

    @RequestMapping(value= {"/{id}"} ,method = RequestMethod.GET)
	public String detail(@PathVariable("id") int id,ModelMap modelMap, Authentication authentication, Evaluate evaluate) {
		var account = accountSelectService.getAccountLogin(authentication);
		var billDetail = billDetailService.find(id);
        modelMap.put("evaluate", evaluate);
		modelMap.put("billDetail", billDetail);
		modelMap.put("account", accountSelectService.getAccountLogin(authentication));
		return "user/bill_evaluate/detail";
	}
	public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

	@RequestMapping(value = {"comment"}, method = RequestMethod.POST)
	public String create(@ModelAttribute("evaluate") Evaluate evaluate, @ModelAttribute("billDetail") BillDetail billDetail_,Authentication authentication, RedirectAttributes redirectAttributes) {
        var billDetail = billDetailService.find(billDetail_.getId());
        var room = roomService.find(billDetail.getRoom().getId());
    var account = accountSelectService.getAccountLogin(authentication);
    evaluate.setStatus(true);
    LocalDate localDate = LocalDate.now();
    Date date = localDateToDate(localDate);
    evaluate.setCreated(date);
    evaluate.setIdAccount(account.getId());
    evaluate.setNameAccount(account.getLastName() + " "+ account.getFirstName());
    evaluate.setIdBill(billDetail.getBill().getId());
    evaluate.setHotel(room.getHotel());

    if (evaluateService.save(evaluate)) {
        Bill bill = billService.find(evaluate.getIdBill());
        bill.setStatus("4");
        billService.save(bill);

        redirectAttributes.addFlashAttribute("msg", "Thank you!");
    }else {
        redirectAttributes.addFlashAttribute("msg", "failed");
    }


	return "redirect:/bill/";
	}

	@RequestMapping(value= {"nocomment"} ,method = RequestMethod.GET)
	public String noComment(ModelMap modelMap, Authentication authentication, Evaluate evaluate) {
		var account = accountSelectService.getAccountLogin(authentication);
		var billDetails = billDetailService.getBillDetails_3(account.getId());
        modelMap.put("evaluate", evaluate);
		modelMap.put("billDetails", billDetails);
		
		return "user/bill_evaluate/noComment";
	}
	@RequestMapping(value= {"edit"} ,method = RequestMethod.GET)
	public String edit(ModelMap modelMap, HttpSession session) {
			
	return "admin/evaluate/edit";
	}
	
	///
	@RequestMapping(value= {"add"} ,method = RequestMethod.POST)
	public String Add(ModelMap modelMap, HttpSession session) {
			
	return "redirect:admin/evaluate/create";
	}
	@RequestMapping(value= {"update"} ,method = RequestMethod.PUT)
	public String update(ModelMap modelMap, HttpSession session) {
			
	return "redirect:/admin/evaluate";
	}
	@RequestMapping(value= {"delete"} ,method = RequestMethod.DELETE)
	public String delete(ModelMap modelMap, HttpSession session) {
			
	return "redirect:admin/evaluate";
	}
	
}

