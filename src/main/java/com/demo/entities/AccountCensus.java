package com.demo.entities;

public class AccountCensus {
	public Long total_hotel_active =0l;
	public Long total_hotel_inactive=0l;
	public Long total_account_role_2=0l;
	public Long total_hotel_null_status=0l;
	public Long getTotal_hotel_active() {
		return total_hotel_active;
	}
	public void setTotal_hotel_active(Long total_hotel_active) {
		this.total_hotel_active = total_hotel_active;
	}
	public Long getTotal_hotel_inactive() {
		return total_hotel_inactive;
	}
	public void setTotal_hotel_inactive(Long total_hotel_inactive) {
		this.total_hotel_inactive = total_hotel_inactive;
	}
	public Long getTotal_account_role_2() {
		return total_account_role_2;
	}
	public void setTotal_account_role_2(Long total_account_role_2) {
		this.total_account_role_2 = total_account_role_2;
	}
	public Long getTotal_hotel_null_status() {
		return total_hotel_null_status;
	}
	public void setTotal_hotel_null_status(Long total_hotel_null_status) {
		this.total_hotel_null_status = total_hotel_null_status;
	}
	@Override
	public String toString() {
		return "AccountCensus [total_hotel_active=" + total_hotel_active + ", total_hotel_inactive="
				+ total_hotel_inactive + ", total_account_role_2=" + total_account_role_2 + ", total_hotel_null_status="
				+ total_hotel_null_status + "]";
	}
	
}
