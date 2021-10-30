package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.CustomerDto;
import com.example.dienthoaiviet.entity.Customer;
import com.example.dienthoaiviet.jpaRepository.CustomerRepository;
import com.example.dienthoaiviet.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public CustomerDto findByPhoneNumber(String phone) {
        Optional<Customer> customer = customerRepository.findByPhoneNumber(phone);
        return customer.isEmpty() ? null : modelMapper.map(customer.get(),CustomerDto.class);
    }
    @Override
    public CustomerDto saveAndFlush(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto,Customer.class);
        customer =customerRepository.saveAndFlush(customer);
        return modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public List<CustomerDto> findAll() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer x : customerRepository.findAll()){
            customerDtoList.add(modelMapper.map(x,CustomerDto.class));
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto findById(Integer id) {
        Optional<Customer> customer =  customerRepository.findById(id);
        return customer.isEmpty() ? null : modelMapper.map(customer,CustomerDto.class);
    }

}
