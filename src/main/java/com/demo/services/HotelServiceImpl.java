package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Hotel;
import com.demo.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{
@Autowired
    private HotelRepository HotelRepository;

    @Override
    public boolean delete(int id) {
        try {
            HotelRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<Hotel> findAll() {
        return HotelRepository.findAll();
    }

    @Override
    public boolean save(Hotel Hotel) {
        try {
            HotelRepository.save(Hotel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Hotel find(int id) {
        return HotelRepository.findById(id).get();
    }
}