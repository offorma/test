package com.project.test.repositories;

import java.util.Date;
import java.util.List;

import com.project.test.model.Event;



public interface EventRepository {

		public void create(Event event);
		public void update(long id, Event event);
		public List<Event> findEventByDate (Date date);
		public Event findEvent (long id);
		public List<Event> findByTutorName(long un);
		public void delete (Long Id);
		public List<Event> getAll();
}
