package com.example.dienthoaiviet.service;

import com.example.dienthoaiviet.dto.RoleDto;
import com.example.dienthoaiviet.entity.Role;

import java.util.List;

public interface IRoleService {
    List<RoleDto> findAll();

    RoleDto getById(Integer aLong);
}
