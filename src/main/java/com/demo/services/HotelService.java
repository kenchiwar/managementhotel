package com.demo.services;

import com.demo.entities.Hotel;

public interface HotelService {
    public Iterable<Hotel> findAll();
    public boolean save(Hotel HotelDetail);
    public boolean delete(int id);
    public Hotel find(int id);
}
