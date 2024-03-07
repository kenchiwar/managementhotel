package com.demo.dtos;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class BillDTO {

    private Integer id;
    private Integer accountDTOID;
    private Integer paymentDTOID;
    private String  paymentName;
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
    private List<BillDetailDTO> billDetails  ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAccountDTOID() {
		return accountDTOID;
	}
	public void setAccountDTOID(Integer accountDTOID) {
		this.accountDTOID = accountDTOID;
	}
	public Integer getPaymentDTOID() {
		return paymentDTOID;
	}
	public void setPaymentDTOID(Integer paymentDTOID) {
		this.paymentDTOID = paymentDTOID;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
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
	public List<BillDetailDTO> getBillDetails() {
		return billDetails;
	}
	public void setBillDetails(List<BillDetailDTO> billDetails) {
		this.billDetails = billDetails;
	}
    
}