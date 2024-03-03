package com.demo.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entities.Account;
import com.demo.entities.Categoryimage;
import com.demo.entities.Hotel;
import com.demo.entities.HotelDetail;
import com.demo.entities.HotelShowIndex;
import com.demo.helpers.SelectAccountHelper;
import com.demo.helpers.SelectHelperHotel;

public interface HotelService {
    public Iterable<Hotel> findAll();
    //idAccount ==null  là edit còn lại là create ; 
    public boolean save(Hotel hotelDetail,
    		MultipartFile fileMain,MultipartFile fileSecondaryPhoto,Integer idAccount);
    public boolean save(Hotel hotelDetail);
    public boolean delete(int id);
    public Hotel find(int id);
    public boolean save(Hotel hotel,MultipartFile[] filArrayAdd,List<Integer> idDeleteArray);
    public boolean authenticationEdit(Hotel hotel ,
    		Authentication authentication) ;
    public List<Account> selectAccount(SelectAccountHelper selectHelper,Integer findById);
    //thêm finb By id thì trả về 1 list có kết quả đầu tiên là id cần tìm 
    public List<HotelShowIndex> hotelShowIndexs(SelectHelperHotel selectHelper,Integer findByid);
    public List<HotelDetail> hotelDetail(SelectHelperHotel selectHelper, Integer findByid) ;
}
