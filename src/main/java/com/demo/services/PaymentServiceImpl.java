package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Payment;
import com.demo.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
@Autowired
    private PaymentRepository PaymentRepository;

    @Override
    public boolean delete(int id) {
        try {
            PaymentRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<Payment> findAll() {
        return PaymentRepository.findAll();
    }

    @Override
    public boolean save(Payment Payment) {
        try {
            PaymentRepository.save(Payment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Payment find(int id) {
        return PaymentRepository.findById(id).get();
    }
}