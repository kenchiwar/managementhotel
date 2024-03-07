package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.entities.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Integer>{
    @Query("from Bill where status ='1'")
    public List<Bill> getBills();

    @Query("from Bill where status ='2'")
    public List<Bill> getBills_2();

    @Query("from Bill where status ='3' or status ='4'")
    public List<Bill> getBills_3_4();

    @Query("from Bill where status ='5'")
    public List<Bill> getBills_5();
}
