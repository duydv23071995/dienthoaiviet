package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.Products_detailsDto;

import java.util.List;

public interface IProduct_detailsService {
    List<Products_detailsDto> findAll();

    Products_detailsDto saveAndFlush(Products_detailsDto s);

    Products_detailsDto getById(String s);

    List<Products_detailsDto> findAllByProductId(String id);

    Products_detailsDto findAllById(String id);
}
