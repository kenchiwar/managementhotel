package com.demo.services;

import com.demo.entities.Evaluate;

public interface EvaluateService {
    public Iterable<Evaluate> findAll();
    public boolean save(Evaluate EvaluateDetail);
    public boolean delete(int id);
    public Evaluate find(int id);
}
