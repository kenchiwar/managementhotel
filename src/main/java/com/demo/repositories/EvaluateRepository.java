package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.entities.Evaluate;

@Repository
public interface EvaluateRepository extends CrudRepository<Evaluate, Integer>{
    @Query("from Evaluate where hotel.id =:idHotel and idAccount =:idAccount")
    public List<Evaluate> getEvaluates_hotel(@Param("idHotel") int idHotel, @Param("idAccount") int idAccount);

    @Query("from Evaluate where hotel.id =:idHotel")
    public List<Evaluate> getEvaluates(@Param("idHotel") int idHotel);
}
