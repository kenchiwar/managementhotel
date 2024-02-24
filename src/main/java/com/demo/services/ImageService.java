package com.demo.services;

import com.demo.entities.Image;

public interface ImageService {
     public Iterable<Image> findAll();
    public boolean save(Image ImageDetail);
    public boolean delete(int id);
    public Image find(int id);
}
