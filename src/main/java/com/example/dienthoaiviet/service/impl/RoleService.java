package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.RoleDto;
import com.example.dienthoaiviet.entity.Role;
import com.example.dienthoaiviet.jpaRepository.RoleRepository;
import com.example.dienthoaiviet.service.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : roleRepository.findAll()){
            roleDtoList.add(modelMapper.map(role,RoleDto.class));
        }
        return roleDtoList;
    }

    @Override
    public RoleDto getById(Integer aLong) {
        return modelMapper.map(roleRepository.getById(aLong),RoleDto.class);
    }
}
