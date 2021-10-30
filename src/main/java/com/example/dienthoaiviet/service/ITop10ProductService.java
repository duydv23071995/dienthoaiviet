package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.Top10ProductDto;

import java.util.List;

public interface ITop10ProductService {
    List<Top10ProductDto> findAllBy();
}
