package com.demo.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.demo.services.AccountService;
import com.demo.services.BillService;
import com.demo.services.CategoryimageService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;






@RestController
@RequestMapping({"api/demo"})
public class ApiFirst {
	@Autowired
	CategoryimageService studentService;
	@Autowired
	HotelService serviceHotel;
	@Autowired ModelMapper modelMapper;
	@Autowired EvaluateService serviceEvaluate;
	@Autowired BillService serviceBill;
	@Autowired AccountService serviceAccount;
	
	@GetMapping(value = {"demo"},produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> demo1 (){
		
		
		return new ResponseEntity<String>("ffffff",HttpStatus.OK);
		
	}
	
	@GetMapping(value = {"demo2"},produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> demo2 (){
		var a =studentService.findAll();
		var c =serviceHotel.find(2);
		var b =new TypeToken<List<EvaluateDTO>>(){}.getType();
		var d =serviceEvaluate.find(6);
		System.out.println(d.isStatus());
		
		var result = modelMapper.map(c,HotelDTO.class);
		
		return new ResponseEntity<Object>(result,HttpStatus.OK);
		
	}
}
