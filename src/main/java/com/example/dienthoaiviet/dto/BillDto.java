package com.example.dienthoaiviet.dto;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {
    private Integer id;
    private Date day = new Date();
    private int status;
    private CustomerDto customer;
    private int sumMoney;
    private String code_Customer;
    private StaffDto staff;
    private int payment;
    private VoucherDto voucher;
    public String convertDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(day);
    }
    public String converTrangthai(){
        if(status==0){
            return "Đang chờ xác nhận";
        }
        if(status==1){
            return "Dang chờ xác nhận, đã  thanh toán";
        }
        if(status==3){
            return "Đơn hàng đã hủy";
        }
        if(status==4){
            return "Đang giao hàng, chưa thanh toán";
        }
        if(status==5){
            return "Đang giao hàng, đã thanh toán";
        }
        if(status==6){
            return "Đơn hàng thành công";
        }
        return "";
    }
}
