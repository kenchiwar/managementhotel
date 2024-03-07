package com.demo.dtos;

import java.util.List;
import java.util.Set;

import com.demo.entities.Hotel;

public class CategoryimageDTO {

    private Integer id;
    private Integer hotelDTOID;
    private String name;
    private List<ImageDTO> images;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHotelDTOID() {
		return hotelDTOID;
	}
	public void setHotelDTOID(Integer hotelDTOID) {
		this.hotelDTOID = hotelDTOID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ImageDTO> getImages() {
		return images;
	}
	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
    
}