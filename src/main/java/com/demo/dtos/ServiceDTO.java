package com.demo.dtos;

import java.util.Set;

public class ServiceDTO {
    private Integer id;
    private String name;
    private Set<RoomDTO> rooms;

    public ServiceDTO() {
    }

    public ServiceDTO(Integer id, String name, Set<RoomDTO> rooms) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomDTO> rooms) {
        this.rooms = rooms;
    }
}
