package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.ImportProductDetailsDto;
import com.example.dienthoaiviet.entity.ImportProductDetails;
import com.example.dienthoaiviet.jpaRepository.ImportProductDetailsRepository;
import com.example.dienthoaiviet.service.IImportProductDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportProductDetailsService implements IImportProductDetailsService {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private  ImportProductDetailsRepository importProductDetailsRepository;

    @Override
    public ImportProductDetailsDto saveAndFlush(ImportProductDetailsDto s) {
        ImportProductDetails importProductDetails = modelMapper.map(s,ImportProductDetails.class);
        importProductDetails = importProductDetailsRepository.saveAndFlush(importProductDetails);
        return modelMapper.map(importProductDetails,ImportProductDetailsDto.class);
    }

    @Override
    public List<ImportProductDetailsDto> findAllById(Integer id) {
        List<ImportProductDetailsDto> list = new ArrayList<>();
        for (ImportProductDetails x : importProductDetailsRepository.findAllById(id)){
            list.add(modelMapper.map(x,ImportProductDetailsDto.class));
        }
        return list;
    }
}
