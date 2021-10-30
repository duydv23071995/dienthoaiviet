package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.ImageDto;
import com.example.dienthoaiviet.entity.Image;
import com.example.dienthoaiviet.jpaRepository.ImageRepsitory;
import com.example.dienthoaiviet.service.IImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ImageService implements IImageService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ImageRepsitory imageRepsitory;

    @Override
    public List<ImageDto> findAllByIdProduct(String id) {
        List<ImageDto> list = new ArrayList<>();
        for (Image x : imageRepsitory.findAllByIdProduct(id)){
            list.add(modelMapper.map(x,ImageDto.class));
        }
        return list;
    }

    @Override
    public ImageDto saveAndFlush(ImageDto imageDto) {
        Image image = modelMapper.map(imageDto,Image.class);
        image = imageRepsitory.saveAndFlush(image);
        return modelMapper.map(image,ImageDto.class);
    }
}
