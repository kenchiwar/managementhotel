package com.demo.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping({"api/hoteldetail"})
public class hotelDetail {
	@Autowired
	CategoryimageService studentService;
	@Autowired
	HotelService serviceHotel;
	@Autowired ModelMapper modelMapper;
	@Autowired EvaluateService serviceEvaluate;
	@Autowired BillService serviceBil;
	@Autowired AccountService serviceAccount;
	
	@GetMapping(value = {"{id}"},produces = MimeTypeUtils.APPLICATION_JSON_VALUE+"; charset=utf-8")
	public ResponseEntity<HotelDTO> FindHotel (@PathVariable("id") Integer id){
		
		try {
			return new ResponseEntity<HotelDTO> (serviceHotel.findDTO(id),HttpStatus.OK);

		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<HotelDTO>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
