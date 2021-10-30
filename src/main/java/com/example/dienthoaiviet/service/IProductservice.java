package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.CategoryDto;
import com.example.dienthoaiviet.dto.ProductsDto;

import java.util.List;

public interface IProductservice {
    ProductsDto saveAndFlush(ProductsDto productsDto);

    List<ProductsDto> findAll();

    ProductsDto findAllById(String id);


    List<ProductsDto> findHotsale();

    List<ProductsDto> findAllByIdCategory(Integer id);

    List<ProductsDto> findAllByIdProperties(Integer id);

    List<ProductsDto> findByIdCategoryOther();

    List<ProductsDto> findByPrice(int priceLow, int expensive);

    List<ProductsDto> findBySort(String category, String properties, int old, int expesite);

    List<ProductsDto> findAllByTest(List<CategoryDto> categoryList);

    List<ProductsDto> findAllByKeyword(String keyWord);
}
