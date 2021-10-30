package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.CategoryDto;
import com.example.dienthoaiviet.dto.ProductsDto;
import com.example.dienthoaiviet.entity.Category;
import com.example.dienthoaiviet.entity.Products;
import com.example.dienthoaiviet.jpaRepository.ProductsRepository;
import com.example.dienthoaiviet.service.IProductservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductservice {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public ProductsDto saveAndFlush(ProductsDto productsDto) {
        Products products =modelMapper.map(productsDto,Products.class);
         products = productsRepository.saveAndFlush(products);
        productsDto = modelMapper.map(products,ProductsDto.class);
        return productsDto;
    }
    @Override
    public List<ProductsDto> findAll() {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for(Products x : productsRepository.findAll()){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
    }

    @Override
    public ProductsDto findAllById(String id) {
        Optional<Products> products = productsRepository.findAllById(id);
        return products.isEmpty() ? null : modelMapper.map(products.get(),ProductsDto.class);
    }


    @Override
    public List<ProductsDto> findHotsale() {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        Pageable pageable =PageRequest.of(0, 10);
        for(Products x : productsRepository.findHotsale(pageable)){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
    }


    @Override
    public List<ProductsDto> findAllByIdCategory(Integer id) {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for(Products x : productsRepository.findAllByIdCategory(id)){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
    }

    @Override
    public List<ProductsDto> findAllByIdProperties(Integer id) {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for(Products x : productsRepository.findAllByIdProperties(id)){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
    }


    @Override
    public List<ProductsDto> findByIdCategoryOther() {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for(Products x : productsRepository.findByIdCategoryOther()){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
    }


    @Override
    public List<ProductsDto> findByPrice(int priceLow, int expensive) {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for(Products x : productsRepository.findByPrice(priceLow,expensive)){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
    }

    @Override
    public List<ProductsDto> findBySort(String category, String properties, int old, int expesite) {
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for(Products x : productsRepository.findBySort(category, properties, old, expesite)){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
//        return productsRepository.findBySort(category, properties, old, expesite);
    }

    @Override
    public List<ProductsDto> findAllByTest(List<CategoryDto> categoryList) {
        List<Category> list =new ArrayList<>();
        for (CategoryDto categoryDto: categoryList){
            list.add(modelMapper.map(categoryDto,Category.class));
        }
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for(Products x :productsRepository.findAllByTest(list)){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
//        return productsRepository.findAllByTest(categoryList);
    }

    @Override
    public List<ProductsDto> findAllByKeyword(String keyWord) {
        String key = "%"+keyWord+"%";
        List<ProductsDto> productsDtoList = new ArrayList<>();
        for(Products x : productsRepository.findAllByKeyword(key)){
            productsDtoList.add(modelMapper.map(x,ProductsDto.class));
        }
        return productsDtoList;
    }
}
