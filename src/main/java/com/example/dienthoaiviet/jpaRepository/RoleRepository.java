package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Override
    List<Role> findAll();

    @Override
    Role getById(Integer aLong);
}
