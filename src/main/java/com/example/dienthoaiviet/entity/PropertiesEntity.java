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
@Table(name = "properties")
public class PropertiesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean status;
    @OneToMany(mappedBy = "properties",fetch = FetchType.LAZY)
    private List<Products> productsList;
}
