package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Top10ProductDto {
    private String id;
    private String name;
    private int price;
    private  int discout;
    private int importprice;
    private String image;
    private long quantity;
    public double hostSale(){
        double sale = discout/100.0;
        return (1-sale)*price;
    }
}
