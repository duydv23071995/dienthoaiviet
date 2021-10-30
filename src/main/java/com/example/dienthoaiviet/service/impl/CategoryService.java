package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.CategoryDto;
import com.example.dienthoaiviet.entity.Category;
import com.example.dienthoaiviet.jpaRepository.CategoryRepository;
import com.example.dienthoaiviet.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    private  ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryDto> list = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        for (Category category : categoryList){
            list.add(modelMapper.map(category,CategoryDto.class));
        }
        return list;
    }

    @Override
    public CategoryDto findById(Integer id) {
       Optional<Category> category = categoryRepository.findById(id);
        return category.isEmpty() ? null : modelMapper.map(category.get(),CategoryDto.class);
    }

    @Override
    public CategoryDto saveAndFlush(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
       category= categoryRepository.saveAndFlush(category);
        return modelMapper.map(category,CategoryDto.class);
    }
}
