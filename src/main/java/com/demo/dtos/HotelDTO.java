package com.demo.dtos;

import java.util.List;
import java.util.Set;

import com.demo.entities.Account;

public class HotelDTO {
	private int idAccount;
	private Integer accountDTOID;
	private String name;
	private String cancellationPolicy;
	private String description;
	private Integer rating;
	private String manager;
	private Boolean status;
	private String mainPhoto;
	private String secondaryPhoto;
	private String papers;
	private String regulation;
	private Integer idHandler;
	private String address;
	private List<CategoryimageDTO> categoryimages;
//
//
//
	private List<EvaluateDTO> evaluates;
  private List<RoomDTO> rooms;
	// private Set<ImagePapersDTO> imagePaperses;
	
	public int getIdAccount() {
		return idAccount;
	}
	public List<RoomDTO> getRooms() {
		return rooms;
	}
	public void setRooms(List<RoomDTO> rooms) {
		this.rooms = rooms;
	}
	public List<EvaluateDTO> getEvaluates() {
		return evaluates;
	}
	public void setEvaluates(List<EvaluateDTO> evaluates) {
		this.evaluates = evaluates;
	}
	
	public List<CategoryimageDTO> getCategoryimages() {
		return categoryimages;
	}
	public void setCategoryimages(List<CategoryimageDTO> categoryimages) {
		this.categoryimages = categoryimages;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public Integer getAccountDTOID() {
		return accountDTOID;
	}
	public void setAccountDTOID(Integer accountDTOID) {
		this.accountDTOID = accountDTOID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCancellationPolicy() {
		return cancellationPolicy;
	}
	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMainPhoto() {
		return mainPhoto;
	}
	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}
	public String getSecondaryPhoto() {
		return secondaryPhoto;
	}
	public void setSecondaryPhoto(String secondaryPhoto) {
		this.secondaryPhoto = secondaryPhoto;
	}
	public String getPapers() {
		return papers;
	}
	public void setPapers(String papers) {
		this.papers = papers;
	}
	public String getRegulation() {
		return regulation;
	}
	public void setRegulation(String regulation) {
		this.regulation = regulation;
	}
	public Integer getIdHandler() {
		return idHandler;
	}
	public void setIdHandler(Integer idHandler) {
		this.idHandler = idHandler;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}