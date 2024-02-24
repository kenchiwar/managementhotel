package com.demo.services;

import com.demo.entities.Complain;

public interface ComplainService {
     public Iterable<Complain> findAll();
    public boolean save(Complain ComplainDetail);
    public boolean delete(int id);
    public Complain find(int id);
}
