package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("select o from Customer o where o.status=true")
    List<Customer> findAll();

    @Query("select o from Customer o where o.status=true and o.id =?1")
    Optional<Customer> findById(Integer id);
    @Query("select o from Customer o where o.status=true and o.phoneNumber=?1")
    Optional<Customer> findByPhoneNumber(String phone);

    @Override
    <S extends Customer> S saveAndFlush(S s);


}
