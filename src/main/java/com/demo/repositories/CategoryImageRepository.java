package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Categoryimage;

@Repository
public interface CategoryImageRepository extends CrudRepository<Categoryimage, Integer>{
    
}
