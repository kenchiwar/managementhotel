package com.demo.dtos;

import java.util.Date;

import com.demo.entities.Hotel;

public class EvaluateDTO {
	private Integer id;
	private Integer hotelDTOID;
	private Integer number;
	private String comment;
	private Date created;
	private Boolean status;
	private Integer idAccount;
	private Integer idBill;
	private String nameAccount;
	//getter setter 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getHotelDTOID() {
		return hotelDTOID;
	}
	public void setHotelDTOID(Integer hotelDTOID) {
		this.hotelDTOID = hotelDTOID;
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
	public Boolean getStatus() {
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