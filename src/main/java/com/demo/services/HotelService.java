package com.demo.services;

import org.springframework.web.multipart.MultipartFile;

import com.demo.entities.Hotel;

public interface HotelService {
    public Iterable<Hotel> findAll();
    //idAccount ==null  là edit còn lại là create ; 
    public boolean save(Hotel hotelDetail,
    		MultipartFile fileMain,MultipartFile fileSecondaryPhoto,Integer idAccount);
    public boolean delete(int id);
    public Hotel find(int id);
}
