package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import java.util.List;


@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
    @Query("from Account where email =:email")
    public Account findByEmail(String email);
    @Query("from Account where email like %:email%")
    public List<Account> findAccountEmail(String email);
}
