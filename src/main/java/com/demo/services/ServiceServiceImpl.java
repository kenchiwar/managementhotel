package com.demo.services;

import com.demo.entities.Service;
import com.demo.repositories.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService{
private ServiceRepository ServiceRepository;

    @Override
    public boolean delete(int id) {
        try {
            ServiceRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<com.demo.entities.Service> findAll() {
        return ServiceRepository.findAll();
    }

    @Override
    public boolean save(Service Service) {
        try {
            ServiceRepository.save(Service);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Service find(int id) {
        return ServiceRepository.findById(id).get();
    }
    
}