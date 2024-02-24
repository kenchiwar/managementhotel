package com.demo.entities;
// Generated Feb 24, 2024, 2:34:54 PM by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;


/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name = "payment", catalog = "managementhotel")
public class Payment implements java.io.Serializable {

	private Integer id;
	private String method;
	private boolean status;
	private Set<Bill> bills = new HashSet<Bill>(0);

	public Payment() {
	}

	public Payment(String method, boolean status) {
		this.method = method;
		this.status = status;
	}

	public Payment(String method, boolean status, Set<Bill> bills) {
		this.method = method;
		this.status = status;
		this.bills = bills;
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

	@Column(name = "method", nullable = false, length = 250)
	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "payment")
	public Set<Bill> getBills() {
		return this.bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

}
