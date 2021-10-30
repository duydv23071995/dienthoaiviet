package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "importproduct_details")
public class ImportProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int quantity;
    @ManyToOne @JoinColumn(name = "importproductid")
    private ImportProduct importProduct;
    @ManyToOne @JoinColumn(name = "productdetailsid")
    private Products_details products_details;
}
