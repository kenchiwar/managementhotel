package com.demo.services;

import java.util.List;

import com.demo.entities.Account;

public interface AccountSelectService {
    public Iterable<Account> findAll();
    public Account findByEmail(String email);
    public boolean save(Account account);
    public boolean delete(int id);
    public Account find(int id);
}
