package com.example.dienthoaiviet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Products_detailsDto {
    private String id;
    private String color;
    private int quantity;
    private ProductsDto products;
}
