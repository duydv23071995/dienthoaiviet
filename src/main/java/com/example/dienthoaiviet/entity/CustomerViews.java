package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "customerviews")
public class CustomerViews implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String note;
    private Date date = new Date();
    private String name;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @ManyToOne @JoinColumn(name = "idproduct")
    private Products products;
}
