package com.example.dienthoaiviet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Products_detailsDto {
    private String id;
    private String color;
    private int quantity;
    private int price ;
    private ProductsDto products;

    public String getConvert_PriceVnd() {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(price)+" VND";
    }
}
