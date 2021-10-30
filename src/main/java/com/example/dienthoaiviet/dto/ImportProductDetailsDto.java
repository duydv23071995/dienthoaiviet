package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportProductDetailsDto {
    private Integer id;
    private int quantity;
    private ImportProductDto importProduct;
    private Products_detailsDto products_details;
}
