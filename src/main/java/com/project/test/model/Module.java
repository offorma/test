package com.project.test.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany (mappedBy = "module")
	private List<Event> event;
	@Column(unique = true, nullable = false)
	private String moduleCode;
	@Column(unique = true, nullable = false)
	private String moduleName;
	@Column(nullable = false)
	private int totalWeeklyHour;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Event> getEvent() {
		return event;
	}
	public void setEvent(List<Event> event) {
		this.event = event;
	}

	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}	 
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	 
	public int getTotalWeeklyHour() {
		return totalWeeklyHour;
	}
	public void setTotalWeeklyHour(int totalWeeklyHour) {
		this.totalWeeklyHour = totalWeeklyHour;
	}
}
