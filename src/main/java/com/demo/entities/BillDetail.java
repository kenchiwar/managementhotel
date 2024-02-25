package com.demo.entities;

import jakarta.persistence.*;

// Generated Feb 25, 2024, 2:30:44 PM by Hibernate Tools 4.3.6.Final



/**
 * BillDetail generated by hbm2java
 */
@Entity
@Table(name = "bill_detail", catalog = "managementhotel")
public class BillDetail implements java.io.Serializable {

	private Integer id;
	private Bill bill;
	private Room room;
	private Double price;
	private Double priceDiscount;
	private Double numberDay;
	private Double total;
	private String reasonDiscount;
	private Integer numberHour;

	public BillDetail() {
	}

	public BillDetail(Bill bill, Room room, Double price, Double priceDiscount, Double numberDay, Double total,
			String reasonDiscount, Integer numberHour) {
		this.bill = bill;
		this.room = room;
		this.price = price;
		this.priceDiscount = priceDiscount;
		this.numberDay = numberDay;
		this.total = total;
		this.reasonDiscount = reasonDiscount;
		this.numberHour = numberHour;
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
	@JoinColumn(name = "idBill")
	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_room")
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "price_discount", precision = 22, scale = 0)
	public Double getPriceDiscount() {
		return this.priceDiscount;
	}

	public void setPriceDiscount(Double priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	@Column(name = "number_day", precision = 22, scale = 0)
	public Double getNumberDay() {
		return this.numberDay;
	}

	public void setNumberDay(Double numberDay) {
		this.numberDay = numberDay;
	}

	@Column(name = "total", precision = 22, scale = 0)
	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "reason_discount", length = 250)
	public String getReasonDiscount() {
		return this.reasonDiscount;
	}

	public void setReasonDiscount(String reasonDiscount) {
		this.reasonDiscount = reasonDiscount;
	}

	@Column(name = "numberHour")
	public Integer getNumberHour() {
		return this.numberHour;
	}

	public void setNumberHour(Integer numberHour) {
		this.numberHour = numberHour;
	}

}
