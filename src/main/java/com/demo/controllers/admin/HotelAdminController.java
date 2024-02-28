package com.demo.controllers.admin;

import java.io.*;

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
import com.demo.services.AccountSelectService;
import com.demo.services.AccountService;
import com.demo.services.HotelService;
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

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session) {

		return "admin/hotel/index";
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

	@RequestMapping(value = { "detail" }, method = RequestMethod.GET)
	public String detail(ModelMap modelMap, HttpSession session) {

		return "admin/hotel/detail";
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

		modelMap.put(AttributeHelper.urlForm, "/" + url + "/edit" + ((id != null) ? "/" + id.toString() : ""));

		modelMap.put(AttributeHelper.checkEdit, true);
		modelMap.put("urlInput", "admin\\hotel\\create");
		modelMap.put("urlImagesHotelMain", AttributeHelper.urlImagesHotelMain);
		modelMap.put("urlImagesHotelCategory", AttributeHelper.urlImagesHotelCategory);

		return "admin/hotel/edit";
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

	@RequestMapping(value = { "edit/status", "edit/{id}/status" }, method = RequestMethod.POST)
	public String editStatus(@RequestParam boolean status
			, @PathVariable(value = "id", required = false) Integer id,
			RedirectAttributes redirect, ModelMap modelMap, Authentication authentication) {
		// Kiểm tra coi có dữ liệu flash chạy qua ko

		Hotel data;
		if (id != null) {
			data = serviceHotel.find(id);
		} else {

			data = selectAccountService.getAccountLogin(authentication).getHotel();
		}
		if (!serviceHotel.authenticationEdit(data, authentication))
			return AttributeHelper.errorPage;
		data.setStatus(status);
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
