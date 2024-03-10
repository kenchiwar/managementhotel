package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Bill;
import com.demo.entities.BillDetail;
import com.demo.repositories.BillDetailRepository;
import com.demo.repositories.BillRepository;

@Service
public class BillDetailServiceImpl implements BillDetailService{
    
    @Autowired
    private BillDetailRepository BillDetailRepository;

    @Override
    public boolean delete(int id) {
        try {
            BillDetailRepository.delete(find(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<BillDetail> findAll() {
        return BillDetailRepository.findAll();
    }

    @Override
    public boolean save(BillDetail BillDetail) {
        try {
            BillDetailRepository.save(BillDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BillDetail find(int id) {
        return BillDetailRepository.findById(id).get();
    }

    @Override
    public List<BillDetail> selectRoom(int id) {
        return BillDetailRepository.selectRoom(id);
    }

     @Override
    public List<BillDetail> getBills() {
        return BillDetailRepository.getBills();
    }

    @Override
    public List<BillDetail> getBills_2() {
        return BillDetailRepository.getBills_2();
    }

    @Override
    public List<BillDetail> getBills_3_4() {
        return BillDetailRepository.getBills_3_4();
    }

    @Override
    public List<BillDetail> getBills_5() {
        return BillDetailRepository.getBills_5();
    }

    @Override
    public BillDetail getBill_User(int id_sender, int id) {
        return BillDetailRepository.getBill_User(id_sender, id);
    }

    @Override
    public List<BillDetail> getBillDetails(int id) {
        return BillDetailRepository.getBillDetails(id);
    }
    @Override
    public List<BillDetail> getBillDetails_1(int id) {
        return BillDetailRepository.getBillDetails_1(id);
    }
    @Override
    public List<BillDetail> getBillDetails_2(int id) {
        return BillDetailRepository.getBillDetails_2(id);
    }

    @Override
    public List<BillDetail> getBillDetails_3(int id) {
        return BillDetailRepository.getBillDetails_3(id);
    }
    
    @Override
    public List<BillDetail> getBillDetails_3_4(int id) {
        return BillDetailRepository.getBillDetails_3_4(id);
    }
    @Override
    public List<BillDetail> getBillDetails_5(int id) {
        return BillDetailRepository.getBillDetails_5(id);
    }

    @Override
    public List<BillDetail> getBillDetails_hotel(int idAccount) {
        return BillDetailRepository.getBillDetails_hotel(idAccount);
    }
    @Override
    public List<BillDetail> getBillDetails_hotel_2(int idAccount) {
        return BillDetailRepository.getBillDetails_hotel_2(idAccount);
    }
    @Override
    public List<BillDetail> getBillDetails_hotel_3_4(int idAccount) {
        return BillDetailRepository.getBillDetails_hotel_3_4(idAccount);
    }
    @Override
    public List<BillDetail> getBillDetails_hotel_5(int idAccount) {
        return BillDetailRepository.getBillDetails_hotel_5(idAccount);
    }
}