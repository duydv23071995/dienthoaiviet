package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.BillDto;

import java.util.List;

public interface IBillService {
    BillDto saveAndFlush(BillDto billDto);

    List<BillDto> findAll(Integer status1, Integer status2);

    BillDto findById(Integer id);

    List<BillDto> findAllBillCustomer(String phone);
}
