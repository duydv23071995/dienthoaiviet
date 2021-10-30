package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.CustomerDto;
import com.example.dienthoaiviet.dto.CustomerViewsDto;

import java.util.List;

public interface ICustomerViewsService {
    List<CustomerViewsDto> findAll(String id);

    CustomerViewsDto saveAndFlush(CustomerViewsDto s);
}
