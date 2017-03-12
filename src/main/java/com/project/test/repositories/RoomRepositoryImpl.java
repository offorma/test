package com.project.test.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.test.model.Room;



@Repository("roomRepository")
public class RoomRepositoryImpl implements RoomRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	Session session;
	
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void create(Room room) {
		 session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		if(room !=null){
		
			try{
				session.save(room);
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
	public void update(long roomId, Room room) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Room r = new Room();
		try{
			r=(Room) session.get(Room.class, roomId);
			r.setRoomNumber(room.getRoomNumber());
			r.setBuilding(room.getBuilding());
			r.setCapacity(room.getCapacity());
			r.setType(room.getType());
			session.update(r); 
	        tx.commit();
	     	session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
			
		}
	
	}

	@Override
	public Room edit(Long Id) {
		return findRoom(Id);	
	}
	@Override
	public Room findRoom(Long roomId) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Room r = new Room();
		try{
		
		 r=(Room) session.get(Room.class, roomId);
		tx.commit();
		session.close();
		
	}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
		
	}
		return r;
		
	}

	@Override
	public Room findByRoomName(String un) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long Id) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
	      try{
	         Room room = 
	                   (Room)session.get(Room.class, Id); 
	         session.delete(room); 
	         tx.commit();
	      }catch(Exception e){
	  		tx.rollback();
	  		session.close();
	  		e.printStackTrace();
	  		
	  	}
		
	}

	@Override
	public List<Room> getAll() {	
		 
		
		 session = sessionFactory.openSession();
	     Transaction tx= session.beginTransaction();
	try{
		
		Query query = session.createQuery("from Room");
		@SuppressWarnings("unchecked")
		ArrayList<Room> r = (ArrayList<Room>) query.list();
				//(List<User>) session.createCriteria(User.class).list();
	
		    if(r.size() > 0){ 
		    	/* for (int i = 0; i < r.size(); i++) {
        		Room label = (Room) r.get(i);
         		System.out.println("Label = " + label.toString());}*/
		        return r;
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
