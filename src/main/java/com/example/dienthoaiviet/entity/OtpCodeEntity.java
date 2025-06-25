package com.example.dienthoaiviet.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "otp_code")
@Entity
@Data
public class OtpCodeEntity {
    @Column(name = "otp")
    @Id
    private String otp;
    @Column(name = "exp_time")
    private Date exp_time;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "use_by")
    private String useBy;
}
