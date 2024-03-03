package com.demo.services;

import java.util.List;

import com.demo.entities.Room;
import com.demo.entities.RoomShowIndex;
import com.demo.helpers.SelectHelperHotel;

public interface RoomService {
    public Iterable<Room> findAll();
    public List<RoomShowIndex> showRoomIndex(SelectHelperHotel selectHelperHotel);
    public boolean save(Room RoomDetail);
    public boolean saveAll(List<Room> rooms);
    public boolean delete(int id);
    public Room find(int id);
  
}
