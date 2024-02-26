package com.demo.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.entities.Hotel;
import com.demo.helpers.FileHelper;
import com.demo.repositories.HotelRepository;
import com.demo.staticHelper.AttributeHelper;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository HotelRepository;

	@Override
	public boolean delete(int id) {
		try {
			HotelRepository.delete(find(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Iterable<Hotel> findAll() {
		return HotelRepository.findAll();
	}

	@Override
	public boolean save(Hotel hotelDetail, MultipartFile fileMain, MultipartFile fileSecondaryPhoto,
			Integer idAccount) {
		
		try {
			if (!fileMain.isEmpty() || !fileSecondaryPhoto.isEmpty()) {
				
				
				File folderImage = new File(new ClassPathResource(".").getFile().getPath() + AttributeHelper.staticUrl
						+ AttributeHelper.urlImagesHotelMain);
				if (!folderImage.exists()) {
					folderImage.mkdirs();
				}
				if (!fileMain.isEmpty()) {
					try {

						String fileName = (idAccount != null)
								? FileHelper.generateFileName(fileMain.getOriginalFilename())
								: hotelDetail.getMainPhoto(); // tạo name
					
						Path path = Paths.get(folderImage.getAbsolutePath() + File.separator + fileName); // tạo path
						Files.copy(fileMain.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);// chỉnh cách
																											// copy vậy
																											// thôi
						System.out.println(folderImage.getAbsolutePath() + File.separator + fileName);
						hotelDetail.setMainPhoto(fileName);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				if (!fileSecondaryPhoto.isEmpty()) {
					try {

						String fileName = (idAccount != null)
								? FileHelper.generateFileName(fileSecondaryPhoto.getOriginalFilename())
								: hotelDetail.getSecondaryPhoto(); // tạo name
						
						Path path = Paths.get(folderImage.getAbsolutePath() + File.separator + fileName); // tạo path
						Files.copy(fileSecondaryPhoto.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);// chỉnh
																													// cách
																													// copy
																													// vậy
																													// thôi
						System.out.println(folderImage.getAbsolutePath() + File.separator + fileName);
						hotelDetail.setSecondaryPhoto(fileName);
						System.out.println("file 2 được");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			
			if ( idAccount!= null) {
				System.out.println(" lan 1");
				hotelDetail.setStatus(false);
				hotelDetail.setAccount(new Account(idAccount));
			}else {
				
				hotelDetail.setAccount(new Account(hotelDetail.getIdAccount()));
			}
			System.out.println(" lan 3");
			HotelRepository.save(hotelDetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Hotel find(int id) {
		return HotelRepository.findById(id).get();
	}
}