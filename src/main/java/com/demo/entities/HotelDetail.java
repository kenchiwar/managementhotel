package com.demo.entities;

public class HotelDetail extends Hotel {
	private Account accountHandler;
	private String metmoi ;
	private String nan ;
	private String[] miano ;
	
	
	

	public String getMetmoi() {
		return metmoi;
	}

	public void setMetmoi(String metmoi) {
		this.metmoi = metmoi;
	}

	public String getNan() {
		return nan;
	}

	public void setNan(String nan) {
		this.nan = nan;
	}

	public Account getAccountHandler() {
		return accountHandler;
	}

	public void setAccountHandler(Account accountHandler) {
		this.accountHandler = accountHandler;
	}
	
	

}
