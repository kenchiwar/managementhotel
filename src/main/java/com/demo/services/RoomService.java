package com.demo.services;

import com.demo.entities.Room;

public interface RoomService {
    public Iterable<Room> findAll();
    public boolean save(Room RoomDetail);
    public boolean delete(int id);
    public Room find(int id);
}
