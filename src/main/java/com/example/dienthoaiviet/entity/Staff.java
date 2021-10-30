package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "staff")
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String password;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "birthday")
    private Date birthDay;
    private String address;
    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;
    private String image;
    private Long salary;
    private boolean gender;
    private boolean status;
    @ManyToOne @JoinColumn(name = "roleid")
    private Role role;
    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY)
    List<Bill> billList;
    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY)
    List<ImportProduct> improtProducts;
}
