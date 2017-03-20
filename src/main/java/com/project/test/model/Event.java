package com.project.test.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "room_id")
	private Room room;
	
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Module module;
	
	@Column(name = "TUTOR", nullable = false)
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Tutor> tutor;
	
	@Column(name = "STUDENT_GROUP", nullable = false)

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<StudentGroup> studentGroup;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
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
