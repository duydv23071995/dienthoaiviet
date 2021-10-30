package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.Bill_detailsDto;
import com.example.dienthoaiviet.entity.Bill_details;
import com.example.dienthoaiviet.jpaRepository.Bill_detailsRepository;
import com.example.dienthoaiviet.service.IBill_detailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Bill_detailsService implements IBill_detailService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private  Bill_detailsRepository bill_detailsRepository;

    @Override
    public Bill_detailsDto saveAndFlush(Bill_detailsDto bill_detailsDto) {
        Bill_details bill_details = modelMapper.map(bill_detailsDto,Bill_details.class);
        bill_details = bill_detailsRepository.saveAndFlush(bill_details);
        return modelMapper.map(bill_details,Bill_detailsDto.class);
    }

    @Override
    public List<Bill_detailsDto> findAllById(Integer id) {
        List<Bill_detailsDto> list = new ArrayList<>();
        for (Bill_details x : bill_detailsRepository.findAllById(id)){
            list.add(modelMapper.map(x,Bill_detailsDto.class));
        }
        return list;
    }
}
