package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.ImagePapers;

@Repository
public interface ImagePapersRepository extends CrudRepository<ImagePapers, Integer>{
    
}
