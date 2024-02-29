package com.demo.dtos;

public class ImagePapersDTO {
    private Integer id;
    private Integer hotelId;
    private String name;

    public ImagePapersDTO() {
    }

    public ImagePapersDTO(Integer id, Integer hotelId, String name) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
