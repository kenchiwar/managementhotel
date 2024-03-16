
package com.demo.controller.api;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.BillDTO;
import com.demo.dtos.CategoryimageDTO;
import com.demo.dtos.EvaluateDTO;
import com.demo.dtos.HotelDTO;
import com.demo.entities.Hotel;
import com.demo.helpers.SelectHelperHotel;
import com.demo.helpers.UrlHelper;
import com.demo.services.AccountService;
import com.demo.services.BillService;
import com.demo.services.CategoryimageService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;
import com.demo.staticHelper.AttributeHelper;






@RestController
@RequestMapping({"api/search"})
public class SearchIndexHotelApi {
	@Autowired
	CategoryimageService studentService;
	@Autowired
	HotelService serviceHotel;
	@Autowired ModelMapper modelMapper;
	@Autowired EvaluateService serviceEvaluate;
	@Autowired BillService serviceBill;
	@Autowired AccountService serviceAccount;
	 @Autowired Environment environment;   //lấy các thuộc tính từ 
	@GetMapping(value = {"demo"},produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> demo1 (){
		
		
		return new ResponseEntity<String>("ffffff",HttpStatus.OK);
		
	}
	
	@GetMapping(value = {"demo2"},produces = MimeTypeUtils.APPLICATION_JSON_VALUE+"; charset=utf-8")
	public ResponseEntity<Object> demo2 (){
		String url = environment.getProperty("BASE_URL")+AttributeHelper.urlImagesHotelMain+"/";
		var e = serviceHotel.hotelShowIndexs(null, null); //hotel show index kho
		e.forEach(data->{
		
			try {
				Integer index = data.getAddress().indexOf(";");
				var address = data.getAddress().substring(0, index);
			data.setAddress(address);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			Long haha = Math.round((double) data.getHaha()/data.getTotalrating());
			//data.setHaha(Math.round())
			data.setHaha(haha);
			data.setMainPhoto(url+data.getMainPhoto());
			data.setSecondaryPhoto(url+data.getSecondaryPhoto());
		});
		HttpHeaders headers = new HttpHeaders();

      


		return new ResponseEntity<Object>( e ,HttpStatus.OK);
		
	}
	@PostMapping(value = {"search"},consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE+"; charset=utf-8")
	public ResponseEntity<Object> demo3 (@RequestBody SelectHelperHotel product){
		String url = environment.getProperty("BASE_URL")+AttributeHelper.urlImagesHotelMain+"/";
		System.out.println(product.getCity());
		System.out.println(product.getDistrict());
		System.out.println(product.getWard());
		System.out.println(product.getPriceMax());
		System.out.println(product.getPriceMin());
		
		var e = serviceHotel.hotelShowIndexs(product, null); //hotel show index kho
		e.forEach(data->{
		
			try {
				Integer index = data.getAddress().indexOf(";");
				var address = data.getAddress().substring(0, index);
			data.setAddress(address);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			Long haha = Math.round((double) data.getHaha()/data.getTotalrating());
			//data.setHaha(Math.round())
			data.setHaha(haha);
			data.setMainPhoto(url+data.getMainPhoto());
			data.setSecondaryPhoto(url+data.getSecondaryPhoto());
		});
		return new ResponseEntity<Object>( e ,HttpStatus.OK);
		
	}
}

