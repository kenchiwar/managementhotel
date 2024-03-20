package com.demo.controllers.user;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import com.demo.entities.HotelShowIndex;
import com.demo.entities.Item;
import com.demo.entities.Room;
import com.demo.helpers.SelectHelperHotel;
import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.BillDetailService;
import com.demo.services.BillService;
import com.demo.services.CartService;
import com.demo.services.HotelService;
import com.demo.services.PaymentService;
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
	@Autowired
	private BillService billService;

	@Autowired
	private AccountSelectService accountSelectService;

	@Autowired
	private CartService cartService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private BillDetailService billDetailService;


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
		return "user/hotel/index";
	}
	@RequestMapping(value = {"/booking/{id}"}, method = RequestMethod.GET)
	public String Booking(ModelMap modelMap, HttpSession session,@PathVariable("id") int id, Authentication authentication) {
		var room = serviceRoom.find(id);
		// var hotel = serviceHotel.find(room.getHotel().getIdAccount());
		Bill bill = new Bill();
		BillDetail billDetail = new BillDetail();
		modelMap.put("bill", bill);
		modelMap.put("billDetail", billDetail);
		modelMap.put("room", room);
		return "user/hotel/booking";
	}

	@RequestMapping(value = "create_bill", method = RequestMethod.POST)
	public String addBill_user(@ModelAttribute("bill") Bill bill ,@ModelAttribute("billDetail") BillDetail billDetail, @ModelAttribute("room") Room room,RedirectAttributes redirectAttributes, HttpSession session, Authentication authentication, ModelMap modelMap) {

			var account = accountSelectService.getAccountLogin(authentication);
			var payment = paymentService.find(1);
			var room_ = roomService.find(room.getId());
			Bill bill_save = new Bill();
			bill_save.setCheckInFrom(bill.getCheckInUntil());
			bill_save.setCheckInUntil(bill.getCheckInUntil());
			bill_save.setCheckOutFrom(bill.getCheckOutFrom());
			bill_save.setMainGuest(room_.getHotel().getIdAccount().toString());
			bill_save.setName(account.getFirstName() + " " + account.getLastName());
			bill_save.setEmail(account.getEmail());
			bill_save.setPhone(account.getPhone());
			bill_save.setPayment(payment);
			bill_save.setStatus("1");
			bill_save.setAccount(account);
			
				var billDetails = new BillDetail();

				// //dem so ngay da o
				long dateBeforeInMs = bill_save.getCheckInUntil().getTime();
				long dateAfterInMs = bill_save.getCheckOutFrom().getTime();
				long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
				long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

 				Date date = bill_save.getCheckInFrom();
				Date date_2 = bill_save.getCheckOutFrom();  // Đối tượng Date của bạn
        		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate localDate_2 = date_2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        		int year = localDate.getYear();
        		int month = localDate.getMonthValue();
        		int day = localDate.getDayOfMonth();

				int year_2 = localDate_2.getYear();
        		int month_2 = localDate_2.getMonthValue();
        		int day_2 = localDate_2.getDayOfMonth();

				LocalDateTime startDateTime = LocalDateTime.of(year, month, day, 14, 0, 0);
        		LocalDateTime endDateTime = LocalDateTime.of(year_2, month_2, day_2, 12, 0, 0);

        		Duration duration = Duration.between(startDateTime, endDateTime);
        		long hours = duration.toHours();

				//create bill details khi tao bill
				billDetails.setBill(bill_save);
				billDetails.setNumberDay((double) daysDiff);
				billDetails.setPrice(room_.getPrice());
				billDetails.setNumberHour((int) hours);
				billDetails.setQuantity(billDetail.getQuantity());
				//giá giảm
				billDetails.setPriceDiscount(room_.getPriceDiscount());
				billDetails.setTotal(billDetails.getPriceDiscount());
				billDetails.setRoom(room_);
				billDetails.getRoom().getId();
				
				Double total;
				Double percent_price = (double)0.1;
				Double percent_price_day = (double)0.05;

				total = ((billDetails.getPriceDiscount() * billDetails.getQuantity()) * (1-percent_price)) * billDetails.getNumberDay() * (1-percent_price_day);
				billDetails.setTotal(total);
				// billDetailService.save(billDetails);

				bill_save.setTotal(billDetails.getTotal());
				// billService.save(bill_save);
				room_.setRoomNow(room_.getRoomNow() - billDetail.getQuantity());

				Item bill_session = new Item(room_, billDetails, billDetail.getQuantity());
				bill_session.setBillDetail(billDetails);
				bill_session.setRoom(room_);

				if ((Item) session.getAttribute("cart") != null) {
					session.removeAttribute("cart");
					session.setAttribute("cart", bill_session);
				}else{
					session.setAttribute("cart", bill_session);
				}
				// roomService.save(room_);
					
				return "redirect:/bill/paypal";
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
		
		Bill bill = new Bill();
		modelMap.put("hotels", hotels);
		modelMap.put("bill", bill);
		session.setAttribute("id_hotel", id);
		if ((Bill) session.getAttribute("bill") != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			var bill_date = (Bill) session.getAttribute("bill");
			var checkInUntil = dateFormat.format(bill_date.getCheckInUntil());
			var checkOutFrom = dateFormat.format(bill_date.getCheckOutFrom());
		modelMap.put("checkInUntil", checkInUntil);
		modelMap.put("checkOutFrom", checkOutFrom);
		}
		
		return "user/hotel/single";
		
	}

}
