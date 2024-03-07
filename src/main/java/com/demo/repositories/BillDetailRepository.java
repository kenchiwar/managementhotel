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

    @Query("from BillDetail where bill.status ='1'")
    public List<BillDetail> getBills();

    @Query("from BillDetail where bill.status ='2'")
    public List<BillDetail> getBills_2();

    @Query("from BillDetail where bill.status ='3' or bill.status ='4'")
    public List<BillDetail> getBills_3_4();

    @Query("from BillDetail where bill.status ='5'")
    public List<BillDetail> getBills_5();
    @Query("from BillDetail where bill.account.id =:id_sender")
    public List<BillDetail> getBillDetails(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='1'")
    public List<BillDetail> getBillDetails_1(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='2'")
    public List<BillDetail> getBillDetails_2(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='3'")
    public List<BillDetail> getBillDetails_3(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='3' or bill.status ='4'")
    public List<BillDetail> getBillDetails_3_4(@Param("id_sender") int id_sender);

    @Query("from BillDetail where bill.account.id =:id_sender and bill.status ='5'")
    public List<BillDetail> getBillDetails_5(@Param("id_sender") int id_sender);
}
