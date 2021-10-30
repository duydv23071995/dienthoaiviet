package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.CustomerViews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerViewsRepository extends JpaRepository<CustomerViews,Integer> {
    @Query("select o from CustomerViews o where o.products.id=?1")
    List<CustomerViews> findAll(String id);

    @Override
    <S extends CustomerViews> S saveAndFlush(S s);
}
