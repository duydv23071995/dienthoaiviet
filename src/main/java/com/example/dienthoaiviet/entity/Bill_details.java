package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "bill_details")
public class Bill_details implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "billid")
    private Bill bill;
    @ManyToOne @JoinColumn(name="products_detailsid")
    private Products_details products_details;
    private int quantity;
}
