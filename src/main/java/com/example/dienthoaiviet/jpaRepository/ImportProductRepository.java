package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.ImportProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImportProductRepository extends JpaRepository<ImportProduct,Integer> {
    @Query("select o from  ImportProduct  o order by o.day desc ")
    List<ImportProduct> findAll();

    @Override
    <S extends ImportProduct> S saveAndFlush(S s);
}
