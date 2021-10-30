package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {
    @Query("select  o from Staff  o where  o.status=true")
    List<Staff> findAll();

    @Override
    <S extends Staff> S saveAndFlush(S s);

    @Query("select o from Staff  o where o.phoneNumber=?1 and  o.status=true")
    Optional<Staff> findByPhone(String phone);

    @Query("select o from Staff o where o.email=?1  and  o.status= true")
    Optional<Staff> findByEmail(String email);

    @Query("select o from Staff o where o.id=?1 and o.status=true")
    Optional<Staff> findById(Integer id);
}
