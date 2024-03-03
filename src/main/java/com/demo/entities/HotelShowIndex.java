package com.demo.entities;

public class HotelShowIndex extends Hotel{
	private Double price ;
	private Double priceDiscount ;
	private Long totalevalate;
	private Integer totalrating;
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
	public Long getTotalevalate() {
		return totalevalate;
	}
	public void setTotalevalate(Long totalevalate) {
		this.totalevalate = totalevalate;
	}
	public Integer getTotalrating() {
		return totalrating;
	}
	public void setTotalrating(Integer totalrating) {
		this.totalrating = totalrating;
	}
	

}
