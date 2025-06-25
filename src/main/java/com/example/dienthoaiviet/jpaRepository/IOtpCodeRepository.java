package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.OtpCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOtpCodeRepository extends JpaRepository<OtpCodeEntity, String> {
    OtpCodeEntity  findFirstByOtpAndUseBy(String otp,String useBy);
}
