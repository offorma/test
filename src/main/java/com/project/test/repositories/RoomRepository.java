package com.project.test.repositories;

import java.util.List;

import com.project.test.model.Room;

public interface RoomRepository {
	public void create(Room room);
	public void update(long roomId, Room room);
	public Room edit (Long Id);
	public Room findRoom(Long roomId);
	public Room findByRoomName(String un);
	public void delete (Long Id);
	public List<Room> getAll();
}
