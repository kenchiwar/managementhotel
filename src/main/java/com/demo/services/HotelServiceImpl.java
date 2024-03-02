package com.demo.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.entities.Hotel;
import com.demo.entities.ImagePapers;
import com.demo.helpers.FileHelper;
import com.demo.repositories.HotelRepository;
import com.demo.repositories.ImagePapersRepository;
import com.demo.repositories.ImageRepository;
import com.demo.staticHelper.AttributeHelper;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository HotelRepository;
	@Autowired private ImagePapersRepository repositoryImagePaper ;
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
				if (fileMain!=null && !fileMain.isEmpty()) {
					try {

						String fileName = 
								 FileHelper.generateFileName(fileMain.getOriginalFilename());
								
					
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

						String fileName = 
								 FileHelper.generateFileName(fileSecondaryPhoto.getOriginalFilename());
								
						
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
	public boolean save(Hotel hotelDetail) {
		try {
			hotelDetail.setAccount(new Account(hotelDetail.getIdAccount()));
			HotelRepository.save(hotelDetail);
			return true;
		} catch (Exception e) {
			return false ;
		}
		
	}
	@Override
	public Hotel find(int id) {
		return HotelRepository.findById(id).get();
	}
	@Override 
	public boolean authenticationEdit(Hotel hotel ,
    		Authentication authentication) {
		return true ;
	}

	@Override
	public boolean save(Hotel hotel, MultipartFile[] filArrayAdd, List<Integer> idDeleteArray) {
		try {
			if ((filArrayAdd != null && filArrayAdd.length > 0) || idDeleteArray != null) {

				File folderImage = new File(new ClassPathResource(".").getFile().getPath() + AttributeHelper.staticUrl
						+ AttributeHelper.urlImagesHotelCategory);
				if (!folderImage.exists()) {
					folderImage.mkdirs();
				}
				if (filArrayAdd != null) {
					List<ImagePapers> dataImport = new ArrayList<ImagePapers>();
					Hotel dataCategory = new Hotel(hotel.getIdAccount());
					for (MultipartFile fileAdd : filArrayAdd) {
						try {
							String fileName = FileHelper.generateFileName(fileAdd.getOriginalFilename());
							// tạo name

							Path path = Paths.get(folderImage.getAbsolutePath() + File.separator + fileName); // tạo
																												// path
							Files.copy(fileAdd.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);// chỉnh
																											// cách
																											// copy vậy
																											// thôi
							System.out.println(folderImage.getAbsolutePath() + File.separator + fileName);

							dataImport.add(new ImagePapers( dataCategory,fileName));

						} catch (Exception e) {
							// TODO: handle exception
						}

						repositoryImagePaper.saveAll(dataImport);
					}
				}
				if (idDeleteArray != null) {
					try {
						Iterable<ImagePapers> dataDelete = repositoryImagePaper.findAllById(idDeleteArray);
						for (ImagePapers fileDelete : dataDelete) {
							try {
								Path path = Paths
										.get(folderImage.getAbsolutePath() + File.separator + fileDelete.getName());
								Files.delete(path);
								// tạo
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						repositoryImagePaper.deleteAllById(idDeleteArray);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}
			return true;
		} catch (Exception e) {
			return false ;
		}
	}
	

}