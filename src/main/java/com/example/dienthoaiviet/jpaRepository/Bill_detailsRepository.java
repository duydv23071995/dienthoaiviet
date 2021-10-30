package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Bill_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Bill_detailsRepository extends JpaRepository<Bill_details,Integer> {
    @Override
    <S extends Bill_details> S saveAndFlush(S s);
    @Query("select o from Bill_details o where o.bill.id=?1 ")
    List<Bill_details> findAllById(Integer id);
}
