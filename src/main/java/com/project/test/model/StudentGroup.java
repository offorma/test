package com.project.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long groupId;
	 @Column(unique = true, nullable = false)
	private String groupName;
	 @Column( nullable = false)
	private int numberOfStudent;
	
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public int getNumberOfStudent() {
		return numberOfStudent;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}
}
