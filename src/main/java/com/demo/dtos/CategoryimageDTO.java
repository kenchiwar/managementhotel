package com.demo.dtos;

import java.util.Set;

public class CategoryimageDTO {

    private Integer id;
    private Integer hotelId;
    private String name;
    private Set<ImageDTO> images;

    public CategoryimageDTO() {
    }

    public CategoryimageDTO(Integer id, Integer hotelId, String name, Set<ImageDTO> images) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.images = images;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ImageDTO> getImages() {
        return images;
    }

    public void setImages(Set<ImageDTO> images) {
        this.images = images;
    }
}
