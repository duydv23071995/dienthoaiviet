package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.ImportProductDto;
import com.example.dienthoaiviet.entity.ImportProduct;
import com.example.dienthoaiviet.jpaRepository.ImportProductRepository;
import com.example.dienthoaiviet.service.IImportProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImprortProductService implements IImportProductService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ImportProductRepository importProductRepository;

    @Override
    public List<ImportProductDto> findAll() {
        List<ImportProductDto> list = new ArrayList<>();
        for(ImportProduct x : importProductRepository.findAll()){
            list.add(modelMapper.map(x,ImportProductDto.class));
        }
        return list;
    }

    @Override
    public ImportProductDto saveAndFlush(ImportProductDto s) {
        ImportProduct importProduct = modelMapper.map(s,ImportProduct.class);
        importProduct = importProductRepository.saveAndFlush(importProduct);
        return modelMapper.map(importProduct,ImportProductDto.class);
    }
}
