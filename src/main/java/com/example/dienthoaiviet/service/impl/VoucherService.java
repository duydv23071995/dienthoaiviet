package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.VoucherDto;
import com.example.dienthoaiviet.entity.Voucher;
import com.example.dienthoaiviet.jpaRepository.VoucherRepository;
import com.example.dienthoaiviet.service.IVoucherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherService implements IVoucherService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<VoucherDto> findAll() {
        List<VoucherDto> voucherDtoList = new ArrayList<>();
        for (Voucher x :  voucherRepository.findAll()){
            voucherDtoList.add(modelMapper.map(x,VoucherDto.class));
        }
        return voucherDtoList;
    }

    @Override
    public VoucherDto  saveAndFlush(VoucherDto voucherDto) {
        Voucher voucher = modelMapper.map(voucherDto,Voucher.class);
        voucher = voucherRepository.saveAndFlush(voucher);
        return modelMapper.map(voucher,VoucherDto.class);
    }

    @Override
    public VoucherDto findAllByCode(String code) {
        Optional<Voucher> voucher = voucherRepository.findAllByCode(code);
        return voucher.isEmpty() ? null : modelMapper.map(voucher.get(),VoucherDto.class);
    }
}
