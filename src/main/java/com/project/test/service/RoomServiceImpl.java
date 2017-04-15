package com.project.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.test.model.Room;
import com.project.test.repositories.RoomRepositoryImpl;

@Service
public class RoomServiceImpl implements RoomService {
@Autowired
RoomRepositoryImpl roomRepo;
	@Override
	public void create(Room room) {
		// TODO Auto-generated method stub
		roomRepo.create(room);
	}

	@Override
	public void update(long roomId, Room room) {
		// TODO Auto-generated method stub
		roomRepo.update(roomId, room);
	}

	@Override
	public Room edit(Long Id) {
		// TODO Auto-generated method stub
		return roomRepo.edit(Id);
	}

	@Override
	public Room findRoom(Long roomId) {
		// TODO Auto-generated method stub
		return roomRepo.findRoom(roomId);
	}

	@Override
	public Room findByRoomName(String un) {
		// TODO Auto-generated method stub
		return roomRepo.findByRoomName(un);
	}

	@Override
	public void delete(Long Id) {
		// TODO Auto-generated method stub
		roomRepo.delete(Id);
	}

	@Override
	public List<Room> getAll() {
		// TODO Auto-generated method stub
		return roomRepo.getAll();
	}

}
