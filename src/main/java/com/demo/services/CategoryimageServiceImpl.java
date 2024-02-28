package com.demo.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entities.Categoryimage;
import com.demo.entities.Image;
import com.demo.helpers.FileHelper;
import com.demo.repositories.CategoryImageRepository;
import com.demo.repositories.ImageRepository;
import com.demo.staticHelper.AttributeHelper;

@Service
public class CategoryimageServiceImpl implements CategoryimageService {
	@Autowired
	private CategoryImageRepository CategoryimageRepository;
	@Autowired
	private ImageRepository repositoryImage;

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

	@Override
	public boolean save(Categoryimage CategoryimageDetail, MultipartFile[] multipartFile, List<Integer> deleteArray) {
		try {
			CategoryimageRepository.save(CategoryimageDetail);

			if ((multipartFile != null && multipartFile.length > 0) || deleteArray != null) {

				File folderImage = new File(new ClassPathResource(".").getFile().getPath() + AttributeHelper.staticUrl
						+ AttributeHelper.urlImagesHotelCategory);
				if (!folderImage.exists()) {
					folderImage.mkdirs();
				}
				if (multipartFile != null && multipartFile.length > 0) {
					List<Image> dataImport = new ArrayList<Image>();
					Categoryimage dataCategory = new Categoryimage(CategoryimageDetail.getId());
					for (MultipartFile fileAdd : multipartFile) {
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

							dataImport.add(new Image(fileName, dataCategory));

						} catch (Exception e) {
							// TODO: handle exception
						}

						repositoryImage.saveAll(dataImport);
					}
				}
				if (deleteArray != null) {
					try {
						Iterable<Image> dataDelete = repositoryImage.findAllById(deleteArray);
						for (Image fileDelete : dataDelete) {
							try {
								Path path = Paths
										.get(folderImage.getAbsolutePath() + File.separator + fileDelete.getName());
								Files.delete(path);
								// tạo
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						repositoryImage.deleteAllById(deleteArray);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}