package com.demo.services;

import com.demo.entities.ImagePapers;

public interface ImagePapersService {
    public Iterable<ImagePapers> findAll();
    public boolean save(ImagePapers ImagePapersDetail);
    public boolean delete(int id);
    public ImagePapers find(int id);
}
