package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.Top10ProductDto;
import com.example.dienthoaiviet.entity.Top10Product;
import com.example.dienthoaiviet.jpaRepository.Top10ProductRepository;
import com.example.dienthoaiviet.service.ITop10ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Top10ProductService implements ITop10ProductService {
    ModelMapper mapper = new ModelMapper();
    @Autowired
    private Top10ProductRepository top10ProductRepository;
    @Override
    public List<Top10ProductDto> findAllBy() {
//        Pageable pageable = PageRequest.of(0, 10);
//        List<Top10ProductDto> productDtoList = new ArrayList<>();
//        System.out.println(top10ProductRepository.findAllBy(pageable));
//        for (Top10Product x :top10ProductRepository.findAllBy(pageable) ){
//            productDtoList.add(mapper.map(x,Top10ProductDto.class));
//        }
//        return productDtoList;
        return new ArrayList<>();
    }
}
