package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
    private Integer id;
    @NotEmpty
//    @Size(min = 4,max = )
    private String password;
    private boolean remember;
}
