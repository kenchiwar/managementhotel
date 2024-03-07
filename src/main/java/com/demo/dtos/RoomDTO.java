package com.demo.dtos;

import java.util.Set;

public class RoomDTO {
    private Integer id;
  
    private String name;
    private Boolean status;
    private Double price;
    private Double priceDiscount;
    private String reasonDiscount;
    private Integer roomMax;
    private Integer roomNow;
    private Integer peopleMin;
    private Integer peopleMax;
    private String describes;
    private Integer hotelDTOID;
	public RoomDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPriceDiscount() {
		return priceDiscount;
	}
	public void setPriceDiscount(Double priceDiscount) {
		this.priceDiscount = priceDiscount;
	}
	public String getReasonDiscount() {
		return reasonDiscount;
	}
	public void setReasonDiscount(String reasonDiscount) {
		this.reasonDiscount = reasonDiscount;
	}
	public Integer getRoomMax() {
		return roomMax;
	}
	public void setRoomMax(Integer roomMax) {
		this.roomMax = roomMax;
	}
	public Integer getRoomNow() {
		return roomNow;
	}
	public void setRoomNow(Integer roomNow) {
		this.roomNow = roomNow;
	}
	public Integer getPeopleMin() {
		return peopleMin;
	}
	public void setPeopleMin(Integer peopleMin) {
		this.peopleMin = peopleMin;
	}
	public Integer getPeopleMax() {
		return peopleMax;
	}
	public void setPeopleMax(Integer peopleMax) {
		this.peopleMax = peopleMax;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public Integer getHotelDTOID() {
		return hotelDTOID;
	}
	public void setHotelDTOID(Integer hotelDTOID) {
		this.hotelDTOID = hotelDTOID;
	}
	
}