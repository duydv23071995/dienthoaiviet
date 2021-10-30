package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDto {
    private Integer id;
    private String code;
    private int value;
    private int quantity;
    private boolean status;
    private String conver_value;
    private Date startDay;
    private Date endDay;

    public String getConver_value() {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(value)+"đ";
    }
}
