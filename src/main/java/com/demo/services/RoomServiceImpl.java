package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Room;
import com.demo.repositories.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomRepository RoomRepository;

	@Override
	public boolean delete(int id) {
		try {
			RoomRepository.delete(find(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Iterable<Room> findAll() {
		return RoomRepository.findAll();
	}

	@Override
	public boolean save(Room Room) {
		try {
			RoomRepository.save(Room);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Room find(int id) {
		return RoomRepository.findById(id).get();
	}
}