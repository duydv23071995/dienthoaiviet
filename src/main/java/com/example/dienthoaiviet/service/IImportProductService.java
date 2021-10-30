package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.ImportProductDto;

import java.util.List;

public interface IImportProductService {
    List<ImportProductDto> findAll();

    ImportProductDto saveAndFlush(ImportProductDto s);
}
