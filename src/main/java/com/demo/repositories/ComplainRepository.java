package com.demo.repositories;

import com.demo.entities.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainRepository extends CrudRepository<Complain, Integer> {
    
}
