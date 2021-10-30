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
@Table(name = "products")
public class Products implements Serializable {
    @Id
    private String id;
    private String details;
    private String name;
    private int price;
    private  int discout;
    private int importprice;
    private String image;
    private  boolean status;
    @ManyToOne @JoinColumn(name = "categoryid")
    private Category category;
    @ManyToOne @JoinColumn(name = "propertiesid")
    private PropertiesEntity properties;
    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Products_details> products_detailsList;
    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private  List<CustomerViews> customerViewsList;
    @OneToMany (mappedBy = "products",fetch = FetchType.LAZY)
    List<Image> listImageProduct;
}
