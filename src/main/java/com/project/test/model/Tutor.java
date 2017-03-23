package com.project.test.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Tutor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	@Column(unique = true, nullable = false)
	private String staffID;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String department;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	 
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	
}
