package com.project.test.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Temporal(TemporalType.DATE)
	@Column( nullable = false)
	private Date date;
	
	@Column( nullable = false)
	private String startTime;
	
	@Column( nullable = false)
	private String endTime;
	@ManyToOne
	private Room room;
	@OneToOne
	private Module module;
	@ManyToMany
	@Column( nullable = false)
	private List<Tutor> tutor;
	@ManyToMany
	@Column( nullable = false)
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
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
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
