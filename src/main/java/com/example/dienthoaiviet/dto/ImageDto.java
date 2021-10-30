package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ImageDto {
    private Integer id;
    @NotEmpty
    private String name;
    private ProductsDto products;
}
