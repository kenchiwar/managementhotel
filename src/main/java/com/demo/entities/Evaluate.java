package com.demo.entities;
// Generated Feb 25, 2024, 2:30:44 PM by Hibernate Tools 4.3.6.Final

import java.util.Date;

import jakarta.persistence.*;
/**
 * Evaluate generated by hbm2java
 */
@Entity
@Table(name = "evaluate", catalog = "managementhotel")
public class Evaluate implements java.io.Serializable {

	private Integer id;
	private Hotel hotel;
	private Integer number;
	private String comment;
	private Date created;
	private boolean status;
	private Integer idAccount;
	private Integer idBill;
	private String nameAccount;

	public Evaluate() {
	}

	public Evaluate(Hotel hotel) {
		this.hotel = hotel;
	}

	public Evaluate(Hotel hotel, Integer number, String comment, Date created,boolean status,Integer idAccount, Integer idBill,
			String nameAccount) {
		this.hotel = hotel;
		this.number = number;
		this.comment = comment;
		this.created = created;
		this.status = status;
		this.idAccount = idAccount;
		this.idBill = idBill;
		this.nameAccount = nameAccount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idHotel", nullable = false)
	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "comment", length = 250)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "status")

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "id_account")
	public Integer getIdAccount() {
		return this.idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	@Column(name = "id_bill")
	public Integer getIdBill() {
		return this.idBill;
	}

	public void setIdBill(Integer idBill) {
		this.idBill = idBill;
	}

	@Column(name = "name_account", length = 250)
	public String getNameAccount() {
		return this.nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}

}
