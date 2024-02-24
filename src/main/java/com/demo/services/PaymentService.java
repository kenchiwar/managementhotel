package com.demo.services;

import com.demo.entities.Payment;

public interface PaymentService {
       public Iterable<Payment> findAll();
    public boolean save(Payment PaymentDetail);
    public boolean delete(int id);
    public Payment find(int id);
}
