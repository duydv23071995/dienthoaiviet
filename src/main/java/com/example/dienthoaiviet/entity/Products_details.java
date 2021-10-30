package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products_details")
public class Products_details implements Serializable {
    @Id
    private String id;
    private String color;
    private int quantity;
    @ManyToOne @JoinColumn(name = "productsid")
    private Products products;
    @OneToMany(mappedBy = "products_details", fetch = FetchType.LAZY)
    private List<Bill_details> bill_detailsList;
    @OneToMany(mappedBy = "products_details", fetch = FetchType.LAZY)
    private List<ImportProductDetails> importProductDetailsList;
}
