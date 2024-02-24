package com.demo.entities;

import jakarta.persistence.*;

// Generated Feb 24, 2024, 2:34:54 PM by Hibernate Tools 4.3.6.Final



/**
 * Service generated by hbm2java
 */
@Entity
@Table(name = "service", catalog = "managementhotel")
public class Service implements java.io.Serializable {

	private Integer id;
	private String name;

	public Service() {
	}

	public Service(String name) {
		this.name = name;
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

	@Column(name = "name", nullable = false, length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
