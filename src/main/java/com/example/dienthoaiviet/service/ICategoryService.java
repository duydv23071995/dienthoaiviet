package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.CategoryDto;
import com.example.dienthoaiviet.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> findAll();

    CategoryDto findById(Integer id);

    CategoryDto saveAndFlush(CategoryDto categoryDto);
}
