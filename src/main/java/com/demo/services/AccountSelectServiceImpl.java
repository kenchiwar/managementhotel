package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.repositories.AccountRepository;

@Service
public class AccountSelectServiceImpl implements AccountSelectService{

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountLogin(Authentication authentication) {
    	
    	 return accountRepository.findByEmail("ffff");
    }
    
    
}