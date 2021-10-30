package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> findAll();

    CustomerDto findById(Integer id);

    CustomerDto findByPhoneNumber(String phone);

    CustomerDto saveAndFlush(CustomerDto customerDto);
}
