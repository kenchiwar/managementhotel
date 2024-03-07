package com.demo.services;

import java.util.List;

import com.demo.entities.Bill;

public interface BillService {
    public Iterable<Bill> findAll();
    public boolean save(Bill BillDetail);
    public boolean delete(int id);
    public Bill find(int id);
   
}
