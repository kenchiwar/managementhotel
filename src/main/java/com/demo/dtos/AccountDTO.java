package com.demo.dtos;

public class AccountDTO {

    private Integer id;
    private Integer roleDTOID;
    private String roleName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String image;
    private String active;
    private Boolean status;
    private Long countNoCommentBill;
    private String eeeee;
    
	
	public String getEeeee() {
		return eeeee;
	}
	public void setEeeee(String eeeee) {
		this.eeeee = eeeee;
	}
	public Long getCountNoCommentBill() {
		return countNoCommentBill;
	}
	public void setCountNoCommentBill(Long countNoCommentBill) {
		this.countNoCommentBill = countNoCommentBill;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleDTOID() {
		return roleDTOID;
	}
	public void setRoleDTOID(Integer roleDTOID) {
		this.roleDTOID = roleDTOID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
    
}