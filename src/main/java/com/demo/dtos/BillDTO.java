package com.demo.dtos;

import java.util.Date;
import java.util.Set;

public class BillDTO {

    private Integer id;
    private Integer accountId;
    private Integer paymentId;
    private String service;
    private Date checkInFrom;
    private Date checkInUntil;
    private Date checkOutFrom;
    private Date checkOutUntil;
    private String mainGuest;
    private String status;
    private String name;
    private String email;
    private String phone;
    private String note;
    private String securityCode;
    private Double total;

    public BillDTO() {
    }

    public BillDTO(Integer id, Integer accountId, Integer paymentId, String service, Date checkInFrom,
                   Date checkInUntil, Date checkOutFrom, Date checkOutUntil, String mainGuest, String status,
                   String name, String email, String phone, String note, String securityCode, Double total) {
        this.id = id;
        this.accountId = accountId;
        this.paymentId = paymentId;
        this.service = service;
        this.checkInFrom = checkInFrom;
        this.checkInUntil = checkInUntil;
        this.checkOutFrom = checkOutFrom;
        this.checkOutUntil = checkOutUntil;
        this.mainGuest = mainGuest;
        this.status = status;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.note = note;
        this.securityCode = securityCode;
        this.total = total;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getCheckInFrom() {
        return checkInFrom;
    }

    public void setCheckInFrom(Date checkInFrom) {
        this.checkInFrom = checkInFrom;
    }

    public Date getCheckInUntil() {
        return checkInUntil;
    }

    public void setCheckInUntil(Date checkInUntil) {
        this.checkInUntil = checkInUntil;
    }

    public Date getCheckOutFrom() {
        return checkOutFrom;
    }

    public void setCheckOutFrom(Date checkOutFrom) {
        this.checkOutFrom = checkOutFrom;
    }

    public Date getCheckOutUntil() {
        return checkOutUntil;
    }

    public void setCheckOutUntil(Date checkOutUntil) {
        this.checkOutUntil = checkOutUntil;
    }

    public String getMainGuest() {
        return mainGuest;
    }

    public void setMainGuest(String mainGuest) {
        this.mainGuest = mainGuest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
