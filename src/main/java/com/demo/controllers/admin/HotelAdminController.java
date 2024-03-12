package com.demo.controllers.admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Hotel;
import com.demo.entities.HotelDetail;
import com.demo.entities.Room;
import com.demo.helpers.SelectAccountHelper;
import com.demo.helpers.UrlHelper;
import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.HotelService;
import com.demo.services.RoomService;
import com.demo.staticHelper.AttributeHelper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/hotel")
public class HotelAdminController {
	private final String url = "admin/hotel";
	private final String template = "admin/hotel";
	private final String dataKey = "hotel";
	@Autowired
	private AccountSelectService selectAccountService;
	@Autowired
	AccountService serviceAccount;
	@Autowired
	private HotelService serviceHotel;
	@Autowired
	private RoomService serviceRoom;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String Index(ModelMap modelMap, Authentication authentication) {
		modelMap.put("urlImagesHotelMain", AttributeHelper.urlImagesHotelMain);
		modelMap.put("adminHotel", "/" + UrlHelper.adminHotel);
		
		
		var hotels = serviceHotel.findAll();
		var accounts = selectAccountService.getAccountLogin(authentication);
		if(accounts.getRole().getId()==1) {
			modelMap.put(dataKey + "s", hotels);
			modelMap.put("checkHandler",true);
		}else {
			List<Hotel> a = new ArrayList<Hotel>();
			hotels.forEach(x->{
				if(x.getIdHandler()!=null && x.getIdHandler()==accounts.getId()) {
					a.add(x);
				}
				
			});
			modelMap.put("checkHandler",false);
			modelMap.put(dataKey + "s", a);

			
		}
		

		//
		modelMap.put(AttributeHelper.urlForm, "/" + url + "/editHandler");

		modelMap.put(AttributeHelper.checkEdit, false);
		// admin đủ quyền mới thấy
		
		modelMap.put("admins", serviceHotel.selectAccount(null, null));
		
		return "admin/hotel/index";
	}

	@RequestMapping(value = { "detail","detail/{id}" }, method = RequestMethod.GET)
	public String detail(ModelMap modelMap, Authentication authentication,
			@PathVariable(name="id",required = false) Integer id) {
		// tùy điều kiện lấy hotel
		Account account =selectAccountService.getAccountLogin(authentication);
		
			modelMap.put("biilCensua", 
					serviceHotel.biilCensua(id));
		if(account.getRole().getId()<=2 && !(id!=null)) modelMap.put("accountCensus", serviceHotel.accountCensus());
		
			
		
		
		
		return "admin/hotel/detail";
	}

	@RequestMapping(value = { "editHandler" }, method = RequestMethod.POST)
	public String updateHandler(ModelMap modelMap, Authentication authentication,
			@RequestParam("idHotel") Integer idHotel, @RequestParam("idAccount") Integer idAccount) {

		Hotel hotel = serviceHotel.find(idHotel);
		hotel.setIdHandler(idAccount);
		serviceHotel.save(hotel);
		System.out.println("fdsfsdf");
		// tùy điều kiện lấy hotel

		return "redirect:/" + url + "";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String create(ModelMap modelMap) {

		modelMap.put(AttributeHelper.urlForm, "/" + url + "/create");

		modelMap.put(AttributeHelper.checkEdit, false);
		if (!(modelMap.get(dataKey) != null))
			modelMap.put(dataKey, new Hotel());

		return template + "/create";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.POST) // nhìn thì biết rồi đó hoặc xài @GetMapping({
																		// "index2" })
	public String create(@ModelAttribute("hotel") Hotel data, @RequestParam("mainPhoto123") MultipartFile fileMain,
			@RequestParam("secondaryPhoto123") MultipartFile fileSecondaryPhoto, RedirectAttributes redirect,
			ModelMap model, Authentication authentication) {

		Account loginAccount = selectAccountService.getAccountLogin(authentication);
		if (serviceHotel.save(data, fileMain, fileSecondaryPhoto, loginAccount.getId())) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success");
			//
			//
			return "redirect:/admin/hotel/index";
		} else {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");
			redirect.addFlashAttribute(dataKey, data);
		}
		;

		// System.out.println(loginAccount.getEmail());

		return "redirect:/" + url + "/create";
	}

	// Edit có id là edit admin ,edit ko id là edit account khách sạn của nó
	@RequestMapping(value = { "edit/{id}", "edit" }, method = RequestMethod.GET)
	public String edit(Authentication authentication, @PathVariable(value = "id", required = false) Integer id,
			ModelMap modelMap) {

		Hotel dataAuthentica;
		// Kiểm tra coi có dữ liệu flash chạy qua ko

		if (id != null) {
			dataAuthentica = serviceHotel.find(id);
		} else {

			dataAuthentica = selectAccountService.getAccountLogin(authentication).getHotel();
		}
		if (!serviceHotel.authenticationEdit(dataAuthentica, authentication))
			return AttributeHelper.errorPage;
		
		modelMap.put(dataKey, dataAuthentica);

		// modelMap.put(dataKey,new Hotel());

		modelMap.put(AttributeHelper.checkEdit, true);
		// các url xử lý

		modelMap.put(AttributeHelper.urlForm, "/" + url + "/edit" + ((id != null) ? "/" + id.toString() : ""));
		modelMap.put("urlInput", "admin\\hotel\\create");
		modelMap.put("urlImagesHotelMain", AttributeHelper.urlImagesHotelMain);
		modelMap.put("urlImagesHotelCategory", AttributeHelper.urlImagesHotelCategory);
		modelMap.put("adminCategoryImage", "/" + UrlHelper.adminCategoryImage);
		modelMap.put("adminHotel", "/" + UrlHelper.adminHotel);
		modelMap.put("adminRoom", "/" + UrlHelper.adminRoom);
		return "admin/hotel/edit";
	}

	@RequestMapping(value = { "editPaper/{id}", "editPaper" }, method = RequestMethod.GET)
	public String editPaper(Authentication authentication, @PathVariable(value = "id", required = false) Integer id,
			ModelMap modelMap) {

		Hotel dataAuthentica;
		// Kiểm tra coi có dữ liệu flash chạy qua ko

		if (id != null) {
			dataAuthentica = serviceHotel.find(id);
		} else {

			dataAuthentica = selectAccountService.getAccountLogin(authentication).getHotel();
		}

		
		modelMap.put(dataKey, dataAuthentica);

		// modelMap.put(dataKey,new Hotel());

		modelMap.put(AttributeHelper.checkEdit, true);

		modelMap.put(AttributeHelper.urlForm, "/" + url + "/editPaper" + ((id != null) ? "/" + id.toString() : ""));
		modelMap.put("urlImagesHotelCategory", AttributeHelper.urlImagesHotelCategory);

		modelMap.put(AttributeHelper.urlReturn, "/" + url + "/edit" + ((id != null) ? "/" + id.toString() : ""));

		return template + "/createPaper";
	}

	////

	@RequestMapping(value = { "edit/{id}", "edit" }, method = RequestMethod.POST)
	public String edit(@ModelAttribute("hotel") Hotel data, @RequestParam("mainPhoto123") MultipartFile fileMain,
			@RequestParam("secondaryPhoto123") MultipartFile fileSecondaryPhoto,
			@PathVariable(value = "id", required = false) Integer id, RedirectAttributes redirect, ModelMap model,
			Authentication authentication) {

		if (serviceHotel.save(data, fileMain, fileSecondaryPhoto, null)) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success");
		} else {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");
			redirect.addFlashAttribute(dataKey, data);
		}
		;

		return "redirect:/" + url + "/edit" + (id != null ? "/" + id : "");
	}

	@RequestMapping(value = { "editPaper/{id}", "editPaper" }, method = RequestMethod.POST)
	public String updatePaper(@RequestParam("fileArrayAdd") MultipartFile[] filArrayAdd,
			@RequestParam(name = "iddeletearray", required = false) List<Integer> idDeleteArray,
			@PathVariable(value = "id", required = false) Integer id, RedirectAttributes redirect, ModelMap model,
			Authentication authentication) {

		Hotel data;
		if (id != null) {
			data = serviceHotel.find(id);
		} else {

			data = selectAccountService.getAccountLogin(authentication).getHotel();
		}
		
		System.out.println("dfsfsdfsfsfdsfsfsf");
		if (serviceHotel.save(data, filArrayAdd, idDeleteArray)) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success");
		} else {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");

		}
		;

		return "redirect:/" + url + "/editPaper" + (id != null ? "/" + id : "");
	}

	@RequestMapping(value = { "edit/status", "edit/{id}/status" }, method = RequestMethod.POST)
	public String editStatus(@RequestParam("status") boolean status,
			@PathVariable(value = "id", required = false) Integer id, RedirectAttributes redirect, ModelMap modelMap,
			Authentication authentication) {

		Hotel data;
		if (id != null) {
			data = serviceHotel.find(id);
		} else {

			data = selectAccountService.getAccountLogin(authentication).getHotel();
		}
	
		data.setStatus(status);
		if (serviceHotel.save(data)) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success");
		} else {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");

		}
		;

		return "redirect:/" + url + "/edit" + (id != null ? "/" + id : "");
	}

	@RequestMapping(value = { "edit/allRoom", "edit/{id}/allRoom" }, method = RequestMethod.POST)
	public String updateAllRoom(@RequestParam("reasonDiscountAll") String reasonDiscountAll,
			@RequestParam("scaleDiscountAll") Integer scaleDiscountAll,
			@PathVariable(value = "id", required = false) Integer id, RedirectAttributes redirect, ModelMap modelMap,
			Authentication authentication) {

		Hotel data;

		if (id != null) {
			data = serviceHotel.find(id);
		} else {

			data = selectAccountService.getAccountLogin(authentication).getHotel();
		}

		data.getRooms().forEach(x -> {
			if (reasonDiscountAll != null && reasonDiscountAll != "")
				x.setReasonDiscount(reasonDiscountAll);
			if (scaleDiscountAll != null && (0 < scaleDiscountAll && scaleDiscountAll < 100))
				x.setPriceDiscount(x.getPrice() * (100 - scaleDiscountAll) / 100);
		});

		if (!serviceRoom.saveAll((new ArrayList<Room>(data.getRooms()))))
			return AttributeHelper.errorPage;

		if (serviceHotel.save(data)) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success");
		} else {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");

		}
		;

		return "redirect:/" + url + "/edit" + (id != null ? "/" + id : "");
	}

	@RequestMapping(value = { "delete" }, method = RequestMethod.DELETE)
	public String delete(ModelMap modelMap, HttpSession session) {

		return "redirect:admin/hotel";
	}

}
