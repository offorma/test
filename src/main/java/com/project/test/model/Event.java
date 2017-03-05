package com.project.test.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	private Date date;
	
	
	private Date startTime;
	
	
	private Date endTime;
	

	@OneToOne
	private Room room;
	
	
	@OneToOne
	private Module module;
	
	@Column(name = "TUTOR", nullable = false)
	@ManyToMany
	private List<Tutor> tutor;
	
	@Column(name = "STUDENT_GROUP", nullable = false)

	@ManyToMany
	private List<StudentGroup> studentGroup;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public List<Tutor> getTutor() {
		return tutor;
	}
	public void setTutor(List<Tutor> tutor) {
		this.tutor = tutor;
	}
	public List<StudentGroup> getStudentGroup() {
		return studentGroup;
	}
	public void setStudentGroup(List<StudentGroup> studentGroup) {
		this.studentGroup = studentGroup;
	}
	
}
