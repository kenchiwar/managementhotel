package com.demo.dtos;

public class ImageDTO {
    private Integer id;
  
    private String name;

    public ImageDTO() {
    }

    public ImageDTO(Integer id, CategoryimageDTO categoryimage, String name) {
        this.id = id;
      
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
