package com.demo.dtos;

public class ImagePapersDTO {
    private Integer id;
    private Integer hotelDTOID;
    private String name;

    public ImagePapersDTO() {
    }

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
    
}