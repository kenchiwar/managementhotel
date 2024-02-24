package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Bill;
import com.demo.repositories.BillRepository;

@Service
public class BillServiceImpl implements BillService{
    @Autowired
    private BillRepository BillRepository;

    @Override
    public boolean delete(int id) {
        try {
            BillRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<Bill> findAll() {
        return BillRepository.findAll();
    }

    @Override
    public boolean save(Bill Bill) {
        try {
            BillRepository.save(Bill);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Bill find(int id) {
        return BillRepository.findById(id).get();
    }
}