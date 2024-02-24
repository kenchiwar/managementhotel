package com.demo.services;

import com.demo.entities.BillDetail;

public interface BillDetailService {
    public Iterable<BillDetail> findAll();
    public boolean save(BillDetail BillDetail);
    public boolean delete(int id);
    public BillDetail find(int id);
}
