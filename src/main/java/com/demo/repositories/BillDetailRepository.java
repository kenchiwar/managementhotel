package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Bill;
import com.demo.entities.BillDetail;

@Repository
public interface BillDetailRepository extends CrudRepository<BillDetail, Integer>{
    @Query("from BillDetail where bill.account.id =:id_room and bill.status = '1' or bill.status = '2'")
    public List<BillDetail> selectRoom(@Param("id_room") int id_room);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='1' or bill.status ='2'")
    public List<BillDetail> getBills(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='2'")
    public List<BillDetail> getBills_2(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='3' or bill.status ='4'")
    public List<BillDetail> getBills_3_4(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='5'")
    public List<BillDetail> getBills_5(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and id=:id")
    public BillDetail getBill_User(@Param("id_sender") int id_sender, @Param("id") int id);

    @Query("from BillDetail where bill.account.id =:id_sender")
    public List<BillDetail> getBillDetails(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and room.hotel.idAccount =:idAccount and bill.status ='1'")
    public List<BillDetail> getBillDetails_1(@Param("id_sender") int id_sender, @Param("idAccount") int idAccount);

    @Query("from BillDetail where bill.account.id =:id_sender and room.hotel.idAccount =:idAccount and bill.status ='2'")
    public List<BillDetail> getBillDetails_2(@Param("id_sender") int id_sender, @Param("idAccount") int idAccount);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='3'")
    public List<BillDetail> getBillDetails_3(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and room.hotel.idAccount =:idAccount and bill.status ='3' or bill.status ='4'")
    public List<BillDetail> getBillDetails_3_4(@Param("id_sender") int id_sender, @Param("idAccount") int idAccount);

    @Query("from BillDetail where bill.account.id =:id_sender and room.hotel.idAccount =:idAccount and bill.status ='5'")
    public List<BillDetail> getBillDetails_5(@Param("id_sender") int id_sender, @Param("idAccount") int idAccount);

    @Query("from BillDetail where room.hotel.idAccount =:idAccount and bill.status ='1'")
    public List<BillDetail> getBillDetails_hotel(@Param("idAccount") int idAccount);

    @Query("from BillDetail where room.hotel.idAccount =:idAccount and bill.status ='2'")
    public List<BillDetail> getBillDetails_hotel_2(@Param("idAccount") int idAccount);

    @Query("from BillDetail where room.hotel.idAccount =:idAccount and bill.status ='3' or bill.status='4'")
    public List<BillDetail> getBillDetails_hotel_3_4(@Param("idAccount") int idAccount);

    @Query("from BillDetail where room.hotel.idAccount =:idAccount and bill.status ='5'")
    public List<BillDetail> getBillDetails_hotel_5(@Param("idAccount") int idAccount);
}
