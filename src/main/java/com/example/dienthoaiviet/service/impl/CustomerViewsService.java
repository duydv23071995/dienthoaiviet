package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.CustomerDto;
import com.example.dienthoaiviet.dto.CustomerViewsDto;
import com.example.dienthoaiviet.entity.CustomerViews;
import com.example.dienthoaiviet.jpaRepository.CustomerViewsRepository;
import com.example.dienthoaiviet.service.ICustomerViewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerViewsService implements ICustomerViewsService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    CustomerViewsRepository customerViewsRepository;

    @Override
    public List<CustomerViewsDto> findAll(String id) {
//        List<CustomerViewsDto> list = new ArrayList<>();
//        for (CustomerViews x : customerViewsRepository.findAll(id)){
//            list.add(modelMapper.map(x,CustomerViewsDto.class));
//        }
        return new ArrayList<>();
    }

    @Override
    public CustomerViewsDto saveAndFlush(CustomerViewsDto s) {
        CustomerViews customerViews = customerViewsRepository.saveAndFlush(modelMapper.map(s,CustomerViews.class));
        return modelMapper.map(customerViews,CustomerViewsDto.class);
    }
}
