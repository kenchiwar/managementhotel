package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Room;
import com.demo.repositories.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomRepository repositoryRoom;

	@Override
	public boolean delete(int id) {
		try {
			repositoryRoom.delete(find(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Iterable<Room> findAll() {
		return repositoryRoom.findAll();
	}

	@Override
	public boolean save(Room Room) {
		try {
			if (!(Room.getPrice() >= 0 && Room.getPriceDiscount() >= 0 && Room.getRoomMax() >= 0
					&& Room.getRoomNow() >= 0 && Room.getPeopleMin() >= 0 && Room.getPeopleMax() >= 0))
				return false;

			repositoryRoom.save(Room);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Room find(int id) {
		return repositoryRoom.findById(id).get();
	}

}