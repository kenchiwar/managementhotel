package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Role;
import com.demo.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	   @Autowired
	   private RoleRepository RoleRepository;

    @Override
    public boolean delete(int id) {
        try {
            RoleRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<Role> findAll() {
        return RoleRepository.findAll();
    }

    @Override
    public boolean save(Role Role) {
        try {
            RoleRepository.save(Role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Role find(int id) {
        return RoleRepository.findById(id).get();
    }
}