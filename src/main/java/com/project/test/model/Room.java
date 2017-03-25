package com.project.test.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
	@Column(unique = true, nullable = false)
	private String roomNumber;
	@Column(nullable=false)
	private int capacity;
	@Column(nullable=false)
	private String building;
	@Column(nullable=false)
	private String type;
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Event> event;
	
	public List<Event> getEvent() {
		return event;
	}
	public void setEvent(List<Event> event) {
		this.event = event;
	}
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void getId(long id) {
		this.id = id;
	}

    
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNumber=" + roomNumber + ", capacity=" + capacity + ", building=" + building
				+ ", type=" + type + ", event=" + event + "]";
	}
}
