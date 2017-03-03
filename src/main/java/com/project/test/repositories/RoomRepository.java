package com.project.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.test.model.Room;

public interface RoomRepository extends JpaRepository <Room, Long > {
	public void addRoom(Room room);
    public void updateRoom(Room room);
    public Room getRoom(long id);
    public void deleteRoom(long id);
    public List<Room> getRooms();
 

}
