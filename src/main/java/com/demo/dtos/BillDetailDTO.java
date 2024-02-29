package com.demo.dtos;

public class BillDetailDTO {

    private Integer id;
    private Integer billId;
    private Integer roomId;
    private Double price;
    private Double priceDiscount;
    private Double numberDay;
    private Double total;
    private String reasonDiscount;
    private Integer numberHour;

    public BillDetailDTO() {
    }

    public BillDetailDTO(Integer id, Integer billId, Integer roomId, Double price, Double priceDiscount,
                         Double numberDay, Double total, String reasonDiscount, Integer numberHour) {
        this.id = id;
        this.billId = billId;
        this.roomId = roomId;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.numberDay = numberDay;
        this.total = total;
        this.reasonDiscount = reasonDiscount;
        this.numberHour = numberHour;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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
