package com.demo.dtos;

import java.util.Set;

public class PaymentDTO {
    private Integer id;
    private String method;
    private boolean status;
    private Set<BillDTO> bills;

    public PaymentDTO() {
    }

    public PaymentDTO(Integer id, String method, boolean status, Set<BillDTO> bills) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.bills = bills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<BillDTO> getBills() {
        return bills;
    }

    public void setBills(Set<BillDTO> bills) {
        this.bills = bills;
    }
}
