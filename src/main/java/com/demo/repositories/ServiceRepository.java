package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Service;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer>{
    
}
