package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepsitory extends JpaRepository<Image,Integer> {
    @Query("select o from Image o where o.products.id=?1")
    List<Image> findAllByIdProduct(String id);

    @Override
    <S extends Image> S saveAndFlush(S s);
}
