package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(email);
		System.out.println(account);
		if (account == null) {
			throw new UsernameNotFoundException("Email Not Found");
		} else {
			if (account.getStatus() == true) {
				List<GrantedAuthority> role = new ArrayList<GrantedAuthority>();
				role.add(new SimpleGrantedAuthority(account.getRole().getName()));
				
				return new User(email, account.getPassword(), role);
			}else {
				throw new UsernameNotFoundException("Account blocked");
			}
		}

	}

	@Override
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	@Override
	public boolean save(Account account) {
		try {
			accountRepository.save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Account find(int id) {
		return accountRepository.findById(id).get();
	}
	@Override
	public boolean delete(int id) {
		try {
			accountRepository.delete(find(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}