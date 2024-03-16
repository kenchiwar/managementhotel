package com.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.entities.Account;

public interface AccountService extends UserDetailsService{
    public Iterable<Account> findAll();
    public Account findByEmail(String email);
    public boolean save(Account account);
    public boolean delete(int id);
    public Account find(int id);
    public List<Account> FindlikeEmail(String email);
}
