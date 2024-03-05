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
import com.demo.entities.Evaluate;
import com.demo.entities.Hotel;
import com.demo.entities.HotelDetail;
import com.demo.entities.HotelShowIndex;
import com.demo.entities.ImagePapers;
import com.demo.entities.Room;
import com.demo.entities.RoomShowIndex;
import com.demo.helpers.FileHelper;
import com.demo.helpers.SelectAccountHelper;
import com.demo.helpers.SelectHelperHotel;
import com.demo.repositories.HotelRepository;
import com.demo.repositories.ImagePapersRepository;
import com.demo.repositories.ImageRepository;
import com.demo.staticHelper.AttributeHelper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import jakarta.persistence.criteria.Subquery;
import jakarta.validation.constraints.Null;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository HotelRepository;
	@Autowired
	EntityManager entityManager;
	@Autowired
	private ImagePapersRepository repositoryImagePaper;

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
				if (fileMain != null && !fileMain.isEmpty()) {
					try {

						String fileName = FileHelper.generateFileName(fileMain.getOriginalFilename());

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

						String fileName = FileHelper.generateFileName(fileSecondaryPhoto.getOriginalFilename());

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

			if (idAccount != null) {
				System.out.println(" lan 1");
				hotelDetail.setStatus(false);
				hotelDetail.setAccount(new Account(idAccount));
			} else {

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
			return false;
		}

	}

	@Override
	public Hotel find(int id) {
		return HotelRepository.findById(id).get();
	}

	@Override
	public boolean authenticationEdit(Hotel hotel, Authentication authentication) {
		return true;
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

							dataImport.add(new ImagePapers(dataCategory, fileName));

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
			return false;
		}
	}

	@Override
	public List<HotelShowIndex> hotelShowIndexs(SelectHelperHotel selectHelper, Integer findByid) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<HotelShowIndex> cq = cb.createQuery(HotelShowIndex.class);
		Root<Hotel> root = cq.from(Hotel.class);
		Predicate whereClause = cb.and();
		var havingClause = cb.and();
		// Join<Hotel, Evaluate> evaluateJoin = root.join("evaluates", JoinType.LEFT);
		Join<Hotel, Room> roomJoin = root.join("rooms", JoinType.LEFT);
		// **1. Subquery for total evaluations (COALESCE(SUM(e1_0.number), ?))**

		Subquery<Long> evaluateSubquery = cq.subquery(Long.class);
		Root<Evaluate> evaluateRoot = evaluateSubquery.from(Evaluate.class);
		evaluateSubquery.select(cb.sum(evaluateRoot.get("number")));
		evaluateSubquery.where(cb.equal(evaluateRoot.get("hotel").get("idAccount"), root.get("idAccount")));
		// **2. Subquery for number of evaluations (COALESCE(COUNT(e1_0.id), ?))**
		Subquery<Long> countSubquery = cq.subquery(Long.class);
		Root<Evaluate> countRoot = countSubquery.from(Evaluate.class);
		countSubquery.select(cb.coalesce(cb.count(countRoot.get("id")), 0L)); // Set default to 0
		countSubquery.where(cb.equal(countRoot.get("hotel").get("idAccount"), root.get("idAccount")));

		// List addd
		List<Selection<?>> selections = Hotel.selection(root);
		// selections.add(cb.coalesce(cb.sum(evaluateJoin.get("number")),
		// 0).alias("totalrating"));
		// selections.add(cb.coalesce(cb.count(evaluateJoin), 0).alias("haha"));
		selections.add(cb.coalesce(evaluateSubquery, 0).alias("haha"));
		selections.add(cb.coalesce(countSubquery, 0).alias("totalrating"));
		var minPrice = cb.coalesce(cb.min(roomJoin.get("price")), 0);
		var maxPrice = cb.coalesce(cb.min(roomJoin.get("priceDiscount")), 0);
		selections.add(minPrice.alias("price"));
		selections.add(maxPrice.alias("priceDiscount"));
		cq.multiselect(selections).groupBy(root.get("idAccount"));
		// whrere
		if (selectHelper != null) {
			if (selectHelper.getCity() != null && !selectHelper.getCity().isBlank()) {
				whereClause = cb.and(whereClause,
						cb.like(root.get("address"), "%city:" + selectHelper.getCity() + "%"));
				System.out.println("1");
				if (selectHelper.getDistrict() != null && !selectHelper.getDistrict().isBlank()) {
					System.out.println("2");
					whereClause = cb.and(whereClause,
							cb.like(root.get("address"), "%district:" + selectHelper.getDistrict() + "%"));
					if (selectHelper.getWard() != null && !selectHelper.getWard().isBlank()) {
						System.out.println("3");
						whereClause = cb.and(whereClause,
								cb.like(root.get("address"), "%ward:" + selectHelper.getDistrict() + "%"));
					}
				}
			}
			if (selectHelper.getPriceMin() != null) {
				System.out.println(selectHelper.getPriceMin());
				havingClause = cb.and(havingClause, cb.greaterThanOrEqualTo(maxPrice, selectHelper.getPriceMin()));
			}
			if (selectHelper.getPriceMax() != null) {
				System.out.println(selectHelper.getPriceMax());
				havingClause = cb.and(havingClause, cb.lessThanOrEqualTo(minPrice, selectHelper.getPriceMax()));
			}
			if (selectHelper.getServices() != null) {
				for (var x : selectHelper.getServices()) {
					whereClause = cb.and(whereClause, cb.like(root.get("description"), "%" + x + "%"));
				}
			}

		}
		// end if wwh
		cq.where(whereClause);
		cq.having(havingClause);
		if (findByid != null) {
			whereClause = cb.and(whereClause, cb.equal(root.get("nhanvienByManvXuly").get("id_account"), findByid));
			cq.where(whereClause);
			List<HotelShowIndex> result = new ArrayList<>();
			result.add(entityManager.createQuery(cq).getSingleResult());
			return result;
		}

		// TODO Auto-generated method stub
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<HotelDetail> hotelDetail(SelectHelperHotel selectHelper, Integer findByid) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<HotelDetail> cq = cb.createQuery(HotelDetail.class);
		Root<Hotel> root = cq.from(Hotel.class);
		Root<Account> rootAccount = cq.from(Account.class);
		Predicate whereClause = cb.and();

		// Join Subquery với Root
//		Subquery<Account> subquery = cq.subquery(Account.class);
//		Root<Account> accountRoot = subquery.from(Account.class);
//		subquery.where(cb.equal(accountRoot.get("id"), root.get("idHandler")));

		// Join Subquery with Root
		// whereClause=cb.and(whereClause,cb.exists(subquery));

		whereClause = cb.and(whereClause, cb.isNotNull(root.get("status")));
		whereClause = cb.and(whereClause, cb.equal(rootAccount.get("id"), root.get("id")));

		List<Selection<?>> selections = Hotel.selection(root);
		// selections.add(subquery.select(accountRoot.get("firstName")).alias("metmoi"));
		selections.add(rootAccount.get("firstName").alias("metmoi"));

		cq.multiselect(selections);

		cq.where(whereClause);
		if (findByid != null) {
			whereClause = cb.and(whereClause, cb.equal(root.get("id_account"), findByid));
			cq.where(whereClause);
			List<HotelDetail> result = new ArrayList<>();
			result.add(entityManager.createQuery(cq).getSingleResult());
			return result;
		}
		System.out.println("ffff");
		// TODO Auto-generated method stub
		return entityManager.createQuery(cq).getResultList();

	}

	@Override
	public List<Account> selectAccount(SelectAccountHelper selectHelper, Integer findById) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
		Root<Account> root = cq.from(Account.class);

		Predicate whereClause = cb.and();
		if (selectHelper != null) {
			if (selectHelper.getRoleMax() != null)
				whereClause = cb.and(whereClause, cb.lessThanOrEqualTo(root.get("role.id"), selectHelper.getRoleMax()))

				;
			if (selectHelper.getRoleMin() != null)
				whereClause = cb.and(whereClause,
						cb.greaterThanOrEqualTo(root.get("role.id"), selectHelper.getRoleMin()))

				;
		}
		cq.where(whereClause);

		// TODO Auto-generated method stub
		return entityManager.createQuery(cq).getResultList();
	}

}