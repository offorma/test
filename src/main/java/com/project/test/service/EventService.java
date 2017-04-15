package com.project.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.test.model.Event;
import com.project.test.model.Room;
import com.project.test.model.StudentGroup;
import com.project.test.model.Tutor;

public interface EventService {

	public void create(Event event);
	public void update(long id, Event event);
	public List<Event> findEventByDate (Date date);
	public List<Event> findEventByRoomAndDate (Room room,Date date);
	public List<Event> findEventByRoom(long id);
	public List<Event> findByTutorName(long un);
	public List<Event> findByRoomAndDate(long id, Date date);
	public ArrayList<StudentGroup> getStudents(ArrayList<String> students);
	public ArrayList<Tutor> getTutor(ArrayList<String> tutors);
	public Event findEvent(Long id);
	public void delete (Long Id);
	public List<Event> getAll();
}
