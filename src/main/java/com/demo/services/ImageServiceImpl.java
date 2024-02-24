package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Image;
import com.demo.repositories.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository ImageRepository;

    @Override
    public boolean delete(int id) {
        try {
            ImageRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<Image> findAll() {
        return ImageRepository.findAll();
    }

    @Override
    public boolean save(Image Image) {
        try {
            ImageRepository.save(Image);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Image find(int id) {
        return ImageRepository.findById(id).get();
    }
}