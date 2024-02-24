package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.entities.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Integer>{
    
}
