package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Evaluate;
import com.demo.repositories.EvaluateRepository;

@Service
public class EvaluateServiceImpl implements EvaluateService{
    @Autowired
    private EvaluateRepository EvaluateRepository;

    @Override
    public boolean delete(int id) {
        try {
            EvaluateRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<Evaluate> findAll() {
        return EvaluateRepository.findAll();
    }

    @Override
    public boolean save(Evaluate Evaluate) {
        try {
            EvaluateRepository.save(Evaluate);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Evaluate find(int id) {
        return EvaluateRepository.findById(id).get();
    }
}