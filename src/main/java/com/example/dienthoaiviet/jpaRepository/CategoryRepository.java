package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("select o from  Category o where o.status=true")
    List<Category> findAll();

    @Query("select  o from Category o where o.status=true and o.id=?1")
    Optional<Category> findById(Integer id);

    @Override
    <S extends Category> S saveAndFlush(S s);
}
