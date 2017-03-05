package com.project.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne (mappedBy = "module")
	private Event event;
	private String moduleCode;
	private String moduleName;
	private int totalWeeklyHour;
	

	public long getModuleId() {
		return id;
	}
	public void setModuleId(long id) {
		this.id = id;
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
