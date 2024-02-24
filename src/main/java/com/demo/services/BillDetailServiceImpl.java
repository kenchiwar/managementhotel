package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.BillDetail;
import com.demo.repositories.BillDetailRepository;

@Service
public class BillDetailServiceImpl implements BillDetailService{
    
    @Autowired
    private BillDetailRepository BillDetailRepository;

    @Override
    public boolean delete(int id) {
        try {
            BillDetailRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<BillDetail> findAll() {
        return BillDetailRepository.findAll();
    }

    @Override
    public boolean save(BillDetail BillDetail) {
        try {
            BillDetailRepository.save(BillDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BillDetail find(int id) {
        return BillDetailRepository.findById(id).get();
    }

    
}