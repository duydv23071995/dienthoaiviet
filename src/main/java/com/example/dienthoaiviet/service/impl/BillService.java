package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.BillDto;
import com.example.dienthoaiviet.entity.Bill;
import com.example.dienthoaiviet.jpaRepository.BillRepository;
import com.example.dienthoaiviet.service.IBillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BillService implements IBillService {
    ModelMapper modelMapper =new ModelMapper();
    @Autowired
    private BillRepository billRepository;

    @Override
    public BillDto saveAndFlush(BillDto billDto) {
        Bill bill = modelMapper.map(billDto,Bill.class);
        bill = billRepository.saveAndFlush(bill);
        return modelMapper.map(bill,BillDto.class);
    }

    @Override
    public List<BillDto> findAll(Integer status1, Integer status2) {
        List<BillDto> billDtos = new ArrayList<>();
        for (Bill x :billRepository.findAll(status1, status2) ){
            billDtos.add(modelMapper.map(x,BillDto.class));
        }
        return billDtos;
    }


    @Override
    public BillDto findById(Integer id) {
        Optional<Bill> bill = billRepository.findById(id);
        return bill.isEmpty() ? null : modelMapper.map(bill.get(),BillDto.class);
    }

    @Override
    public List<BillDto> findAllBillCustomer(String phone) {
        List<BillDto> billDtos = new ArrayList<>();
        for (Bill x :billRepository.findAllBillCustomer(phone) ){
            billDtos.add(modelMapper.map(x,BillDto.class));
        }
        return billDtos;
    }
}
