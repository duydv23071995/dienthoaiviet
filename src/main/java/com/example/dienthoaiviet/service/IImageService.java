package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.ImageDto;

import java.util.List;

public interface IImageService {
    List<ImageDto> findAllByIdProduct(String id);

    ImageDto saveAndFlush(ImageDto imageDto);
}
