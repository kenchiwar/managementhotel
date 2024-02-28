package com.demo.controllers.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Categoryimage;
import com.demo.entities.Hotel;
import com.demo.helpers.UrlHelper;
import com.demo.services.CategoryimageService;
import com.demo.staticHelper.AttributeHelper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/category/image")
public class ImageCategoryAdminController {

	private final String template = "admin/categoryimage";
	private final String url = UrlHelper.adminCategoryImage;
	private final String dataKey = "categoryImage";
	@Autowired private CategoryimageService serviceCategoryimage;
	// Template

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String Index(ModelMap modelMap, HttpSession session) {

		return "admin/categoryimage/index";
	}

	@RequestMapping(value = { "create/{idHotel}" }, method = RequestMethod.GET)
	public String create(@PathVariable("idHotel") Integer idHotel,
			ModelMap modelMap) {
		modelMap.put(AttributeHelper.urlForm, "/" + url + "/create/" + idHotel);

		modelMap.put(AttributeHelper.checkEdit, false);
		if (!(modelMap.get(dataKey) != null)) {
			Categoryimage dataOutput = new Categoryimage();
			dataOutput.setHotel(new Hotel(idHotel));
			modelMap.put(dataKey, dataOutput);
		}

		return template + "/create";
	}

	// Method
	@RequestMapping(value = { "create/{idHotel}" }, method = RequestMethod.POST)
	public String Add(
			@ModelAttribute(dataKey) Categoryimage data,
			@PathVariable("idHotel") Integer idHotel,
			@RequestParam("fileArrayAdd") MultipartFile[] filArrayAdd,
			RedirectAttributes redirect,
			ModelMap modelMap) {
		
		if (serviceCategoryimage.save(data, filArrayAdd, null)) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success");
			
		} else {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");
			redirect.addFlashAttribute(dataKey, data);
		}
		
		return "redirect:/"+url+"/create/"+idHotel;
	}

	@RequestMapping(value = { "detail" }, method = RequestMethod.GET)
	public String detail(ModelMap modelMap, HttpSession session) {
		
		return "admin/categoryimage/detail";
	}

	@RequestMapping(value = { "edit/{idHotel}/{idCategory}" }, method = RequestMethod.GET)
	public String edit(
			@PathVariable("idHotel") Integer idHotel,
			@PathVariable("idCategory") Integer idCategory,
			ModelMap modelMap, HttpSession session) {
		modelMap.put(dataKey, serviceCategoryimage.find(idCategory));
		
	String url =	create(idHotel,modelMap);
		modelMap.put(AttributeHelper.urlForm,
				"/"+this.url+"/edit/"+idHotel+"/"+idCategory);
		modelMap.put(AttributeHelper.checkEdit, true);
		
		modelMap.put("urlImagesHotelCategory", AttributeHelper.urlImagesHotelCategory);
		return url;
	}

	@RequestMapping(value = { "edit/{idHotel}/{idCategory}" }, method = RequestMethod.POST)
	public String update(
			@ModelAttribute(dataKey) Categoryimage data,
			@PathVariable("idHotel") Integer idHotel,
			@PathVariable("idCategory") Integer idCategory,
			@RequestParam("fileArrayAdd") MultipartFile[] filArrayAdd,
			@RequestParam(name = "iddeletearray", required = false)  List<Integer> idDeleteArray,
			RedirectAttributes redirect,
			ModelMap modelMap) {
		if (idDeleteArray != null) {
            for (Integer option : idDeleteArray) {
                System.out.println(option);
            }
        }
		System.out.println("fffffaaa");
		if (serviceCategoryimage.save(data, filArrayAdd, idDeleteArray)) {
			redirect.addFlashAttribute(AttributeHelper.successAlert, "Success");
			
		} else {
			redirect.addFlashAttribute(AttributeHelper.errorAlert, "Error");
			redirect.addFlashAttribute(dataKey, data);
		}
		
		return "redirect:/"+this.url+"/edit/"+idHotel+"/"+idCategory;
	}

	@RequestMapping(value = { "edit/{idHotel}/{idCategory}/delete" }, method = RequestMethod.POST)
	public String delete(
			@PathVariable("idHotel") Integer idHotel,
			@PathVariable("idCategory") Integer idCategory,
			ModelMap modelMap, HttpSession session) {
		serviceCategoryimage.delete(idCategory) ;
		
		return "redirect:/"+UrlHelper.adminHotel+"/edit"+"/"+idHotel;
	}

}
