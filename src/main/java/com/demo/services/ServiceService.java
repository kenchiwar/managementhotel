package com.demo.services;

import com.demo.entities.Service;

public interface ServiceService {
     public Iterable<Service> findAll();
    public boolean save(Service ServiceDetail);
    public boolean delete(int id);
    public Service find(int id);
}
