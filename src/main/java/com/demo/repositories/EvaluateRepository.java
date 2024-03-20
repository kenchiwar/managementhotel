package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.entities.Evaluate;

@Repository
public interface EvaluateRepository extends CrudRepository<Evaluate, Integer>{
    @Query("from Evaluate where hotel.id =:idHotel and idAccount =:idAccount ORDER BY id DESC")
    public List<Evaluate> getEvaluates_hotel(@Param("idHotel") int idHotel, @Param("idAccount") int idAccount);

    @Query("from Evaluate where hotel.id =:idHotel ORDER BY id DESC")
    public List<Evaluate> getEvaluates(@Param("idHotel") int idHotel);

    @Query("from Evaluate where idBill =:idBill and idAccount =:idAccount ORDER BY id DESC")
    public Evaluate getEvaluate_bill(@Param("idBill") int idBill, @Param("idAccount") int idAccount);

    @Query("from Evaluate where idAccount =:idSender ORDER BY id DESC")
    public List<Evaluate> getEvaluate_user(@Param("idSender") int idSender);
}
