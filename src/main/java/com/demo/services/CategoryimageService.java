package com.demo.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.entities.Categoryimage;

public interface CategoryimageService {
    public Iterable<Categoryimage> findAll();
    public boolean save(Categoryimage CategoryimageDetail);
    public boolean save(Categoryimage CategoryimageDetail,MultipartFile[] filArrayAdd,List<Integer> idDeleteArray);
    public boolean delete(int id);
    public Categoryimage find(int id);
}
