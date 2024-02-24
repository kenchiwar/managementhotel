package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer>{
    
}
