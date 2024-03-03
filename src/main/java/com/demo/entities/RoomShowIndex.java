/**
 * 
 */
package com.demo.entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

/**
 * 
 */
public class RoomShowIndex extends Room {
	private Long totalevalate;
	private Integer totalrating;

	private String hotelName;
	
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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	

}
