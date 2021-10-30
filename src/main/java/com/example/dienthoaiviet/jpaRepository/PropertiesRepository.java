package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.PropertiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertiesRepository extends JpaRepository<PropertiesEntity, Integer> {
    @Query("select o from PropertiesEntity o where o.status=true")
    List<PropertiesEntity> findAll();

    @Query("select o from PropertiesEntity o where  o.id =?1 and o.status=true")
    Optional<PropertiesEntity> findById(Integer id);

    @Override
    <S extends PropertiesEntity> S saveAndFlush(S s);
}
