package com.demo.services;

import com.demo.entities.Role;

public interface RoleService {
        public Iterable<Role> findAll();
    public boolean save(Role RoleDetail);
    public boolean delete(int id);
    public Role find(int id);
}
