package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fullname")
    private  String fullName;
    private String address;
    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;
    private boolean status;
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Bill> billList;
}
