package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.VoucherDto;

import java.util.List;

public interface IVoucherService {
    List<VoucherDto> findAll();

    VoucherDto  saveAndFlush(VoucherDto voucherDto);

    VoucherDto findAllByCode(String code);
}
