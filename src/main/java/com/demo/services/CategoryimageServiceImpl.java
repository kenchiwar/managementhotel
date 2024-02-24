package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Categoryimage;
import com.demo.repositories.CategoryImageRepository;

@Service
public class CategoryimageServiceImpl implements CategoryimageService{
    @Autowired
    private CategoryImageRepository CategoryimageRepository;

    @Override
    public boolean delete(int id) {
        try {
            CategoryimageRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<Categoryimage> findAll() {
        return CategoryimageRepository.findAll();
    }

    @Override
    public boolean save(Categoryimage Categoryimage) {
        try {
            CategoryimageRepository.save(Categoryimage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Categoryimage find(int id) {
        return CategoryimageRepository.findById(id).get();
    }
}