package com.demo.dtos;

import com.demo.entities.Categoryimage;

public class ImageDTO {
    private Integer id;
    private Integer categoryimageDTOID;
    private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCategoryimageDTOID() {
		return categoryimageDTOID;
	}
	public void setCategoryimageDTOID(Integer categoryimageDTOID) {
		this.categoryimageDTOID = categoryimageDTOID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}