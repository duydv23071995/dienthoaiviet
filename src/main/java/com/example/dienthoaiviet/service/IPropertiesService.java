package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.PropertiesDto;

import java.util.List;

public interface IPropertiesService {
    List<PropertiesDto> findAll();

    PropertiesDto findById(Integer id);

    PropertiesDto saveAndFlush(PropertiesDto propertiesDto);
}
