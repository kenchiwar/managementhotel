package com.demo.dtos;

import java.util.Set;

public class PaymentDTO {
    private Integer id;
    private String method;
    private Boolean status;
  

    public PaymentDTO() {
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


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}
    
  }