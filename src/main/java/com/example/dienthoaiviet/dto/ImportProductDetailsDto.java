package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportProductDetailsDto {
    private Integer id;
    private int quantity;
    private int price;
    private ImportProductDto importProduct;
    private Products_detailsDto products_details;
    private String priceVnd;

    public String getPriceVnd() {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(price)+" VND";
    }

}
