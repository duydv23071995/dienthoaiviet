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
@Table(name = "voucher")
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private int value;
    private int quantity;
    private boolean status;
    @Column(name = "startday")
    private Date startDay;
    @Column(name = "endday")
    private Date endDay;
    @OneToMany(mappedBy = "voucher")
    private List<Bill> list;
}
