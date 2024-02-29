package com.demo.controllers.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.demo.entities.Categoryimage;
import com.demo.entities.Hotel;
import com.demo.entities.Room;
import com.demo.helpers.UrlHelper;
import com.demo.services.HotelService;
import com.demo.services.RoomService;
import com.demo.staticHelper.AttributeHelper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/room")
public class RoomAdminController {

	private final String template = "admin/room";
	private final String url = UrlHelper.adminRoom;
	private final String dataKey = "roomData";
	// Template
	@Autowired
	private HotelService serviceHotel;
	@Autowired
	private RoomService serviceRoom;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session) {

		return "admin/room/index";
	}

	@RequestMapping(value = { "create/{idHotel}" }, method = RequestMethod.GET)
	public String create(@PathVariable("idHotel") Integer idHotel, 
			ModelMap modelMap) {

		modelMap.put(AttributeHelper.urlForm, "/" + url + "/create/" + idHotel);

		modelMap.put(AttributeHelper.checkEdit, false);
		modelMap.put(AttributeHelper.urlReturn, "/" + UrlHelper.adminHotel + "/edit/" + idHotel);
		if (!(modelMap.get(dataKey) != null)) {
			System.out.println("fdfsfsdf");
			Room dataOutput = new Room(new Hotel(idHotel));
			dataOutput.setStatus(true);
			
			modelMap.put(dataKey, dataOutput);
		}

		return template + "/create";

	}

	@RequestMapping(value = { "detail" }, method = RequestMethod.GET)
	public String detail(
			
			ModelMap modelMap
			) {

		return "admin/room/detail";
	}

	@RequestMapping(value = { "edit/{idHotel}/{idRoom}" }, method = RequestMethod.GET)
	public String edit(@PathVariable("idHotel") Integer idHotel,
			@PathVariable("idRoom") Integer idRoom,
			ModelMap modelMap) {
		
		modelMap.put(dataKey, serviceRoom.find(idRoom));
		
		String templateCreate=create(idHotel, modelMap);
		modelMap.put(AttributeHelper.urlForm, "/" + url + "/edit/" + idHotel+"/"+idRoom);
		
		modelMap.put(AttributeHelper.checkEdit, true);
				return templateCreate ;
	}
	
	// Method
	@RequestMapping(value = { "create/{idHotel}" }, method = RequestMethod.POST)
	public String Add(@PathVariable("idHotel") Integer idHotel, @ModelAttribute(dataKey) Room data, 
			RedirectAttributes redirect,
			ModelMap modelMap) {

		/*
		 * if (!serviceHotel.authenticationEdit(new Hotel(idHotel), authentication))
		 * return AttributeHelpSer.errorPage;
		 */
		
		if(data.getHotel().getIdAccount() != idHotel) {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");
			return "redirect:/"+url+"/create/"+idHotel;
		}
		
		if(serviceRoom.save(data)) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success");
			
		}else {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");
			redirect.addFlashAttribute(dataKey, data);

		}
		return "redirect:/"+url+"/create/"+idHotel;
	}

	@RequestMapping(value = { "edit/{idHotel}/{idRoom}" }, method = RequestMethod.POST)
	public String update(
			@PathVariable("idHotel") Integer idHotel,
			@PathVariable("idRoom") Integer idRoom,
			@ModelAttribute(dataKey) Room data,
			RedirectAttributes redirect,
			ModelMap modelMap) {
		Add(idHotel, data, redirect, modelMap);
		return "redirect:/"+url+"/edit/"+idHotel+"/"+idRoom;
	}

	@RequestMapping(value = { "edit/{idHotel}/{idRoom}/delete" }, method = RequestMethod.POST)
	public String delete(
			@PathVariable("idHotel") Integer idHotel,
			@PathVariable("idRoom") Integer idRoom,
			RedirectAttributes redirect,
			ModelMap modelMap, HttpSession session) {
		
		
		if(serviceRoom.delete(idRoom)) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success Delete");
			return "redirect:/"+UrlHelper.adminHotel+"/edit/"+idHotel ;
		}else {
			
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error Delete");
			return "redirect:/"+url+"/edit/"+idHotel+"/"+idRoom ;

		}
		
		
		
	}
	@RequestMapping(value = { "edit/{idHotel}/{idRoom}/status" }, method = RequestMethod.POST)
	public String updateStatus(@RequestParam("status") Boolean status ,
			@PathVariable("idHotel") Integer idHotel,
			@PathVariable("idRoom") Integer idRoom,
			
			ModelMap modelMap, HttpSession session) {
		try {
			
			Room hay =  serviceRoom.find(idRoom);
			hay.setStatus(status);
			serviceRoom.save(hay);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "redirect:/"+url+"/edit/"+idHotel+"/"+idRoom;
	}
	

}
