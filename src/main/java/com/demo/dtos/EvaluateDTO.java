package com.demo.dtos;

import java.util.Date;

import com.demo.entities.Hotel;

public class EvaluateDTO {
	private Integer id;

	private Integer number;
	private String comment;
	private Date created;
	private Boolean status;
	private Integer idHotel;
	private Integer idAccount;
	private Integer idBill;
	private String nameAccount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}
	public Boolean getStatus() {
		return status;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Boolean isStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}
	public Integer getIdBill() {
		return idBill;
	}
	public void setIdBill(Integer idBill) {
		this.idBill = idBill;
	}
	public String getNameAccount() {
		return nameAccount;
	}
	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}
	
	
}