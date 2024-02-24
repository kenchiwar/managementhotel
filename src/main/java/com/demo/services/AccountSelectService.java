package com.demo.services;

import java.util.List;

import com.demo.entities.Account;
import org.springframework.security.core.Authentication;
public interface AccountSelectService {
	public Account getAccountLogin(Authentication authentication);
}
