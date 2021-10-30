package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.ImportProductDetailsDto;

import java.util.List;

public interface IImportProductDetailsService {
    ImportProductDetailsDto saveAndFlush(ImportProductDetailsDto s);

    List<ImportProductDetailsDto> findAllById(Integer id);
}
