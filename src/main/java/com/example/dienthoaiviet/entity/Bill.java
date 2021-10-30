package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date day = new Date();
    private int status;
    @Column(name = "summoney")
    private int sumMoney;
    @ManyToOne @JoinColumn(name = "customerid")
    private Customer customer;
    @ManyToOne @JoinColumn(name = "staffid",nullable = true)
    private Staff staff;
    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    private List<Bill_details> bill_details;
    @ManyToOne @JoinColumn(name = "voucherid")
    private Voucher voucher;

}
