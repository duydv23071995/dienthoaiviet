package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.PropertiesDto;
import com.example.dienthoaiviet.entity.PropertiesEntity;
import com.example.dienthoaiviet.jpaRepository.PropertiesRepository;
import com.example.dienthoaiviet.service.IPropertiesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertiesService implements IPropertiesService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    PropertiesRepository propertiesRepository;

    @Override
    public List<PropertiesDto> findAll() {
       List<PropertiesDto> list = new ArrayList<>();
       for (PropertiesEntity x : propertiesRepository.findAll()){
            list.add(modelMapper.map(x,PropertiesDto.class));
       }

        return list;
    }

    @Override
    public PropertiesDto findById(Integer id) {
       Optional<PropertiesEntity> propertiesEntity = propertiesRepository.findById(id);
       return modelMapper.map(propertiesEntity.get(),PropertiesDto.class);
    }

    @Override
    public PropertiesDto saveAndFlush(PropertiesDto propertiesDto) {
        PropertiesEntity propertiesEntity = propertiesRepository.saveAndFlush(modelMapper.map(propertiesDto,PropertiesEntity.class));
        return modelMapper.map(propertiesEntity,PropertiesDto.class);
    }
}
