package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.BillConfirmDto;
import com.example.dienthoaiviet.dto.BillDto;
import com.example.dienthoaiviet.dto.Bill_detailsDto;
import com.example.dienthoaiviet.dto.Products_detailsDto;
import com.example.dienthoaiviet.service.IBillService;
import com.example.dienthoaiviet.service.IBill_detailService;
import com.example.dienthoaiviet.service.IProduct_detailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bill")
public class biiController {
    @Autowired
    private IBillService billService;
    @Autowired
    private IBill_detailService bill_detailService;
    @Autowired
    private IProduct_detailsService product_detailsService;
    @GetMapping("/transpost")
    public  String getBillTrans(Model model){
        model.addAttribute("lisBillCGH",billService.findAll(4,5));
        return "admin/Bill/billtranspost";
    }
    @GetMapping("/voice")
    public  String getInvoive(Model model){
        model.addAttribute("lisBillDHT",billService.findAll(6,6));
        return "admin/Bill/billinvoice";
    }
    @GetMapping
    public  String findAll(Model model){
        model.addAttribute("listBIllCXN",billService.findAll(0,1));
        return "admin/Bill/bill";
    }
    @GetMapping("/details/{id}")
    @ResponseBody
    public ResponseEntity<?> billDetails(@PathVariable Integer id,Model model){
        List<Bill_detailsDto> bill_detailsDtoList = bill_detailService.findAllById(id);
        if(bill_detailsDtoList.isEmpty()){
           return ResponseEntity.ok("no product");
        }
        return ResponseEntity.ok(bill_detailsDtoList);
    }
    @DeleteMapping("/details")
    public ResponseEntity<?> deleteBillDetails(@RequestBody BillConfirmDto billConfirmDto){
        BillDto bill = billService.findById(billConfirmDto.getId());
        if(billConfirmDto.getStatus().equals("chuathanhtoan")){
            List<Bill_detailsDto> bill_detailsDtoList = bill_detailService.findAllById(bill.getId());
            for (Bill_detailsDto x : bill_detailsDtoList){
                Products_detailsDto products_details = product_detailsService.findAllById(x.getProducts_details().getId());
                products_details.setQuantity(products_details.getQuantity()+x.getQuantity());
                product_detailsService.saveAndFlush(products_details);
            }
        }
        bill.setStatus(3);
        bill = billService.saveAndFlush(bill);
        return ResponseEntity.ok(bill);
    }
    @PostMapping("/details")
    @ResponseBody
    public ResponseEntity<?> addBill(@RequestBody BillConfirmDto billConfirmDto){
        BillDto bill = billService.findById(billConfirmDto.getId());
        if(billConfirmDto.getStatus().equals("xacnhan")){
            if(bill.getStatus()==0){
                bill.setStatus(4);
            }else {
                bill.setStatus(5);
            }
            List<Bill_detailsDto> bill_detailsDtoList = bill_detailService.findAllById(bill.getId());
            for (Bill_detailsDto x : bill_detailsDtoList){
                Products_detailsDto products_details = product_detailsService.findAllById(x.getProducts_details().getId());
                if(x.getQuantity()>products_details.getQuantity()){
                    return ResponseEntity.ok("no quantity");
                }
                products_details.setQuantity(products_details.getQuantity()-x.getQuantity());
                product_detailsService.saveAndFlush(products_details);
            }
        }
        if(billConfirmDto.getStatus().equals("chuathanhtoan")){
            bill.setStatus(6);
        }
        bill = billService.saveAndFlush(bill);
        return ResponseEntity.ok(bill);
    }

}
