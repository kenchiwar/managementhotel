package com.demo.entities;
// Generated Feb 25, 2024, 2:30:44 PM by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;


/**
 * Account generated by hbm2java
 */
@Entity
@Table(name = "account", catalog = "managementhotel", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Account implements java.io.Serializable {

	private Integer id;
	private Role role;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private String image;
	private String active;
	private Boolean status;
	private Set<Bill> bills = new HashSet<Bill>(0);
	private Hotel hotel;
	private Set<Complain> complainsForSender = new HashSet<Complain>(0);
	private Set<Complain> complainsForHandler = new HashSet<Complain>(0);

	public Account() {
	}

	public Account(Role role) {
		this.role = role;
	}

	public Account(Role role, String firstName, String lastName, String email, String password, String phone,
			String image, String active, Boolean status, Set<Bill> bills, Hotel hotel, Set<Complain> complainsForSender,
			Set<Complain> complainsForHandler) {
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.image = image;
		this.active = active;
		this.status = status;
		this.bills = bills;
		this.hotel = hotel;
		this.complainsForSender = complainsForSender;
		this.complainsForHandler = complainsForHandler;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_role", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "first_name", length = 250)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 250)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", unique = true, length = 250)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", length = 250)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "phone", length = 250)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "image", length = 250)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "active", length = 250)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Bill> getBills() {
		return this.bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountBySender")
	public Set<Complain> getComplainsForSender() {
		return this.complainsForSender;
	}

	public void setComplainsForSender(Set<Complain> complainsForSender) {
		this.complainsForSender = complainsForSender;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountByHandler")
	public Set<Complain> getComplainsForHandler() {
		return this.complainsForHandler;
	}

	public void setComplainsForHandler(Set<Complain> complainsForHandler) {
		this.complainsForHandler = complainsForHandler;
	}

}
