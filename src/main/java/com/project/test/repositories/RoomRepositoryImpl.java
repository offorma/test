package com.project.test.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.test.model.Room;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
	
	    @Autowired
	    private SessionFactory sessionFactory;
	     
	    private Session getCurrentSession() {
	        return sessionFactory.getCurrentSession();
	    }
	 
	    public void addRoom(Room room) {
	        getCurrentSession().save(room);
	    }
	 
	    public void updateRoom(Room room) {
	    	Room roomToUpdate = getRoomNumber(room.getId());
	    	roomToUpdate.setRoomNumber(room.getRoomNumber());
	  
	        getCurrentSession().update(roomToUpdate);
	         
	    }
	 
	    public Room getRoomNumber(long id) {
	    	Room room = (Room) getCurrentSession().get(Room.class, id);
	        return room;
	    }
	 
	    public void deleteRoom(int id) {
	    	Room room = getRoomNumber(id);
	        if (room != null)
	            getCurrentSession().delete(room);
	    }
	 
	    @SuppressWarnings("unchecked")
	    public List<Room> getRooms() {
	        return getCurrentSession().createQuery("from Room").list();
	    }
}
