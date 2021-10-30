package com.example.dienthoaiviet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Bill_detailsDto {
    private Integer id;
    private BillDto bill;
    private Products_detailsDto products_details;
    @NotEmpty()
    @DecimalMin("1")
    private int quantity;
}
