package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    @Override
    <S extends Bill> S saveAndFlush(S s);
    @Query("select o from  Bill o where o.status in (?1,?2) order by o.id desc ")
    List<Bill> findAll(Integer status1 , Integer status2);
    @Query("select o from  Bill o where o.id=?1")
    Optional<Bill> findById(Integer id);
    @Query("select o from Bill  o where o.customer.phoneNumber=?1 order by o.id desc ")
    List<Bill> findAllBillCustomer(String phone);


}
