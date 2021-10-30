package com.example.dienthoaiviet.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer id;
    @NotEmpty
    private String name;
    private boolean status;
}
