package com.demo.services;

import java.util.List;

import com.demo.entities.Bill;
import com.demo.entities.BillDetail;

public interface BillDetailService {
    public Iterable<BillDetail> findAll();
    public boolean save(BillDetail BillDetail);
    public boolean delete(int id);
    public BillDetail find(int id);
    public List<BillDetail> selectRoom(int id);

    public List<BillDetail> getBills(int id_sender);
    public List<BillDetail> getBills_2(int id_sender);
    public List<BillDetail> getBills_3_4(int id_sender);
    public List<BillDetail> getBills_5(int id_sender);
    public BillDetail getBill_User(int id_sender, int id);

    public List<BillDetail> getBillDetails(int id);
    public List<BillDetail> getBillDetails_1(int id, int idAccount);
    public List<BillDetail> getBillDetails_2(int id, int idAccount);
    public List<BillDetail> getBillDetails_3(int id);
    public List<BillDetail> getBillDetails_3_4(int id, int idAccount);
    public List<BillDetail> getBillDetails_5(int id, int idAccount);

    public List<BillDetail> getBillDetails_hotel(int idAccount);
    public List<BillDetail> getBillDetails_hotel_2(int idAccount);
    public List<BillDetail> getBillDetails_hotel_3_4(int idAccount);
    public List<BillDetail> getBillDetails_hotel_5(int idAccount);

}
