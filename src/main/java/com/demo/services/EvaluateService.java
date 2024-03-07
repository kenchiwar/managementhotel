package com.demo.services;

import java.util.List;

import com.demo.entities.Evaluate;

public interface EvaluateService {
    public Iterable<Evaluate> findAll();
    public boolean save(Evaluate EvaluateDetail);
    public boolean delete(int id);
    public Evaluate find(int id);

    public List<Evaluate> getEvaluates_hotel(int idHotel, int idAccount);
    public List<Evaluate> getEvaluates(int idHotel);

}
