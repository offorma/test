package com.project.test.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.test.model.Event;
import com.project.test.model.Tutor;


@Repository
public class EventRepositoryImpl implements EventRepository {
	@Autowired
	SessionFactory sessionFactory;
	
	Session session;
	
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	@Override
	public void create(Event event) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		if(event !=null){
		
			try{
				session.save(event);
	            tx.commit();
		        session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		}
	}

	@Override
	public void update(long id, Event event) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Event ev = new Event();
		try{
			ev=(Event) session.get(Event.class, id);
			ev.setDate(event.getDate());
			ev.setStartTime(event.getStartTime());
			ev.setEndTime(event.getEndTime());
			ev.setModule(event.getModule());
			ev.setRoom(event.getRoom());
			ev.setStudentGroup(event.getStudentGroup());
			ev.setTutor(event.getTutor());
			session.update(ev); 
	        tx.commit();
	     	session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findEventByDate(Date date) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		ArrayList<Event> ev = new ArrayList<Event>();
		try{
			Query query = session.createQuery("from Event where date = :date");
			query.setParameter("date", date);
			ev  = (ArrayList<Event>) query.list();
			tx.commit();
			session.close();
		
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
			}
		return ev;
		
	}

	@Override
	public Event findEvent (long id) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Event ev = new Event();
		try{
		ev=(Event) session.get(Event.class, id);
		tx.commit();
		session.close();
		
	}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
		
	}
		return ev;
			}


	@Override
	public List<Event> findByTutorName(long id) {
		ArrayList<Event> ev = new ArrayList<Event>();
		ArrayList<Event> event=(ArrayList<Event>) getAll();
		for(Event myevent:event){
			ArrayList<Tutor> tut= (ArrayList<Tutor>) myevent.getTutor();
			for(Tutor tutor:tut){
				if(tutor.getId()==id){
					ev.add(myevent);
				}
			}
		}
		return ev;
	}

	@Override
	public void delete(Long Id) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
	      try{
	        Event event= 
	                   (Event)session.get(Event.class, Id);
	        event.setRoom(null);
	        event.setModule(null);
	        event.setStudentGroup(null);
	        event.setTutor(null);
	         session.update(event);
	         Query q = session.createQuery("delete Event where id = :id");
	         q.setParameter("id", Id);
	         q.executeUpdate();
	         //session.delete(event); 
	         tx.commit();
	      }catch(Exception e){
	  		tx.rollback();
	  		session.close();
	  		e.printStackTrace();
	  		
	  	}
		
	}

	@Override
	public List<Event> getAll() {
		session = sessionFactory.openSession();
	     Transaction tx= session.beginTransaction();
	try{
		
		Query query = session.createQuery("from Event");
		@SuppressWarnings("unchecked")
		ArrayList<Event> m = (ArrayList<Event>) query.list();
				//(List<User>) session.createCriteria(User.class).list();
	
		    if(m.size() > 0){ 
		    	/* for (int i = 0; i < r.size(); i++) {
      		Room label = (Room) r.get(i);
       		System.out.println("Label = " + label.toString());}*/
		        return m;
		    }
		    return null;  
		  }
	catch(Exception e){
		tx.rollback();
 		session.close();
 		e.printStackTrace();
		
			    }
	return null;
	}

}
