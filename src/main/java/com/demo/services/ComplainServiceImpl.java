package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Complain;
import com.demo.repositories.ComplainRepository;

@Service
public class ComplainServiceImpl implements ComplainService{

    @Autowired
    private ComplainRepository ComplainRepository;

    @Override
    public boolean delete(int id) {
        try {
            ComplainRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<Complain> findAll() {
        return ComplainRepository.findAll();
    }

    @Override
    public boolean save(Complain Complain) {
        try {
            ComplainRepository.save(Complain);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Complain find(int id) {
        return ComplainRepository.findById(id).get();
    }
}