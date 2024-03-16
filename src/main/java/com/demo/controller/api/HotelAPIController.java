package com.demo.controller.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import com.demo.dtos.EvaluateDTO;
import com.demo.dtos.HotelDTO;
import com.demo.services.AccountService;
import com.demo.services.BillService;
import com.demo.services.CategoryimageService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;
import com.demo.staticHelper.AttributeHelper;

@RestController
@RequestMapping({"api/hotel"})
public class HotelAPIController {
    CategoryimageService studentService;
	@Autowired
	HotelService serviceHotel;
	@Autowired ModelMapper modelMapper;
	@Autowired EvaluateService serviceEvaluate;
	@Autowired BillService serviceBill;
	@Autowired AccountService serviceAccount;

    @Autowired 
    private Environment environment;

   @GetMapping(value = {"", "/"},produces = MimeTypeUtils.APPLICATION_JSON_VALUE+"; charset=utf-8")
	public ResponseEntity<Object> hotel (){
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
		return new ResponseEntity<Object>( e ,HttpStatus.OK);
		
	}
}
