package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.Bill_detailsDto;

import java.util.List;

public interface IBill_detailService {
    Bill_detailsDto saveAndFlush(Bill_detailsDto bill_detailsDto);

    List<Bill_detailsDto> findAllById(Integer id);
}
