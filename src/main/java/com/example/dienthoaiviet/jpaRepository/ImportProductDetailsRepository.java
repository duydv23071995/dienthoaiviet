package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.ImportProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImportProductDetailsRepository extends JpaRepository<ImportProductDetails,Integer> {
    @Override
    <S extends ImportProductDetails> S saveAndFlush(S s);
    @Query("select o from ImportProductDetails o where o.importProduct.id=?1")
    List<ImportProductDetails> findAllById(Integer id);
}
