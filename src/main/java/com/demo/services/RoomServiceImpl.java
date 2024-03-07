package com.demo.services;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Evaluate;
import com.demo.entities.Hotel;
import com.demo.entities.Room;
import com.demo.entities.RoomShowIndex;
import com.demo.helpers.SelectHelperHotel;
import com.demo.repositories.RoomRepository;

import jakarta.persistence.EntityManager;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomRepository repositoryRoom;
	@Autowired
	EntityManager entityManager;

	@Override
	public boolean delete(int id) {
		try {
			repositoryRoom.delete(find(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Iterable<Room> findAll() {
		return repositoryRoom.findAll();
	}

	@Override
	public boolean save(Room Room) {
		try {
			if (!(Room.getPrice() >= 0 && Room.getPriceDiscount() >= 0 && Room.getRoomMax() >= 0
					&& Room.getRoomNow() >= 0 && Room.getPeopleMin() >= 0 && Room.getPeopleMax() >= 0))
				return false;

			repositoryRoom.save(Room);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Room find(int id) {
		return repositoryRoom.findById(id).get();
	}


	@Override
	public boolean saveAll(List<Room> rooms) {
		try {

			repositoryRoom.saveAll(rooms);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<RoomShowIndex> showRoomIndex(SelectHelperHotel selectHeper) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RoomShowIndex> cq = cb.createQuery(RoomShowIndex.class);
		Root<Room> root = cq.from(Room.class);
		Predicate whereClause = cb.and();

		Join<Room, Hotel> hotelJoin = root.join("hotel");
		Join<Hotel, Evaluate> evaluateJoin = hotelJoin.join("evaluates", JoinType.LEFT);
		
		
		//cq.multiselect(root.get("id").alias("id"), root.get("name").alias("name"));
		List<Selection<?>> selections = Room.selection(root);
		selections.add(hotelJoin.get("name").alias("hotelName"));
		selections.add(
				cb.coalesce(cb.sum(evaluateJoin.get("number")), 0)
				.alias("totalrating"));
		selections.add(	cb.coalesce(cb.count(evaluateJoin), 0L).alias("totalevalate"));
		cq.multiselect(selections).groupBy(root.get("id"));
		
		cq.where(whereClause);
		// TODO Auto-generated method stub
		return entityManager.createQuery(cq).getResultList();
	}


}