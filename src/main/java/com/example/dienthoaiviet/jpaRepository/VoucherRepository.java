package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Query("select o from Voucher o where o.status=true")
    List<Voucher> findAll();

    @Override
    <S extends Voucher> S saveAndFlush(S s);

    @Query("select o from Voucher o where o.status= true and o.code=?1 and o.quantity>0")
    Optional<Voucher> findAllByCode(String code);
}
