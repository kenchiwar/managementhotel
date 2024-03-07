package com.demo.dtos;

public class BillDetailDTO {

    private Integer id;
    private Integer billDTOID;
    private Integer roomDTOID;
    private String roomName;
    private Double price;
    private Double priceDiscount;
    private Double numberDay;
    private Double total;
    private String reasonDiscount;
    private Integer numberHour;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBillDTOID() {
		return billDTOID;
	}
	public void setBillDTOID(Integer billDTOID) {
		this.billDTOID = billDTOID;
	}
	public Integer getRoomDTOID() {
		return roomDTOID;
	}
	public void setRoomDTOID(Integer roomDTOID) {
		this.roomDTOID = roomDTOID;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
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
	public Double getNumberDay() {
		return numberDay;
	}
	public void setNumberDay(Double numberDay) {
		this.numberDay = numberDay;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getReasonDiscount() {
		return reasonDiscount;
	}
	public void setReasonDiscount(String reasonDiscount) {
		this.reasonDiscount = reasonDiscount;
	}
	public Integer getNumberHour() {
		return numberHour;
	}
	public void setNumberHour(Integer numberHour) {
		this.numberHour = numberHour;
	}
    
}