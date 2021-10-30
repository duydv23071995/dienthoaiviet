package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Products_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Product_detailsRepository extends JpaRepository<Products_details,String> {
    @Query("select o from Products_details o where o.products.status=true")

    List<Products_details> findAll();

    @Override
    <S extends Products_details> S saveAndFlush(S s);

    @Override
    Products_details getById(String s);
    @Query("select o from Products_details o where o.products.status=true and o.products.id = ?1")
    List<Products_details> findAllByProductId(String id);
    @Query("select o from Products_details o where  o.products.status=true and o.id=?1")
    Optional<Products_details> findAllById(String id);
}
