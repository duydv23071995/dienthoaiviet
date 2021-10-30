package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.Products_detailsDto;
import com.example.dienthoaiviet.entity.Products_details;
import com.example.dienthoaiviet.jpaRepository.Product_detailsRepository;
import com.example.dienthoaiviet.service.IProduct_detailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Product_detailsService implements IProduct_detailsService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private Product_detailsRepository product_detailsRepository;

    @Override
    public List<Products_detailsDto> findAll() {
        List<Products_detailsDto> list = new ArrayList<>();
        for(Products_details x : product_detailsRepository.findAll()){
            list.add(modelMapper.map(x,Products_detailsDto.class));
        }
        return list;
    }

    @Override
    public Products_detailsDto saveAndFlush(Products_detailsDto s) {
        Products_details products_details= modelMapper.map(s,Products_details.class);
        products_details = product_detailsRepository.saveAndFlush(products_details);

        return modelMapper.map(products_details,Products_detailsDto.class);
    }

    @Override
    public Products_detailsDto getById(String s) {
        Products_details products_details = product_detailsRepository.getById(s);
        return modelMapper.map(products_details,Products_detailsDto.class);
    }

    @Override
    public List<Products_detailsDto> findAllByProductId(String id) {
        List<Products_detailsDto> list = new ArrayList<>();
        for(Products_details x : product_detailsRepository.findAllByProductId(id)){
            list.add(modelMapper.map(x,Products_detailsDto.class));
        }
        return list;
    }

    @Override
    public Products_detailsDto findAllById(String id) {
        Optional<Products_details> products_details = product_detailsRepository.findAllById(id);
        return products_details.isEmpty() ? null: modelMapper.map(products_details.get(),Products_detailsDto.class);
    }
}
