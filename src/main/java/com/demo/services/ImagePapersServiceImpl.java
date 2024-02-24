package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.ImagePapers;
import com.demo.repositories.ImagePapersRepository;

@Service
public class ImagePapersServiceImpl implements ImagePapersService{

    @Autowired
    private ImagePapersRepository ImagePapersRepository;

    @Override
    public boolean delete(int id) {
        try {
            ImagePapersRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<ImagePapers> findAll() {
        return ImagePapersRepository.findAll();
    }

    @Override
    public boolean save(ImagePapers ImagePapers) {
        try {
            ImagePapersRepository.save(ImagePapers);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ImagePapers find(int id) {
        return ImagePapersRepository.findById(id).get();
    }
}