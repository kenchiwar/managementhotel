package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.entities.Evaluate;

@Repository
public interface EvaluateRepository extends CrudRepository<Evaluate, Integer>{
    
}
