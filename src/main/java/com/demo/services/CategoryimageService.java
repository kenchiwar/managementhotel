package com.demo.services;

import com.demo.entities.Categoryimage;

public interface CategoryimageService {
    public Iterable<Categoryimage> findAll();
    public boolean save(Categoryimage CategoryimageDetail);
    public boolean delete(int id);
    public Categoryimage find(int id);
}
