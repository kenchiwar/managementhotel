package com.demo.dtos;

public class EvaluateDTO {

    private Integer id;
    private Integer hotelId;
    private Integer number;
    private String comment;
    private Integer accountId;
    private Integer billId;
    private String accountName;

    public EvaluateDTO() {
    }

    public EvaluateDTO(Integer id, Integer hotelId, Integer number, String comment, Integer accountId,
                       Integer billId, String accountName) {
        this.id = id;
        this.hotelId = hotelId;
        this.number = number;
        this.comment = comment;
        this.accountId = accountId;
        this.billId = billId;
        this.accountName = accountName;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
