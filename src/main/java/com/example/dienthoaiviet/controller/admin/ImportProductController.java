package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.ImportProductDetailsDto;
import com.example.dienthoaiviet.dto.ImportProductDto;
import com.example.dienthoaiviet.dto.Products_detailsDto;
import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.service.IImportProductDetailsService;
import com.example.dienthoaiviet.service.IImportProductService;
import com.example.dienthoaiviet.service.IProduct_detailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("importproduct")
public class ImportProductController {
    @Autowired
    private IProduct_detailsService product_detailsService;
    @Autowired
    private IImportProductService importProductService;
    @Autowired
    private  IImportProductDetailsService iImportProductDetailsService;
    @Autowired
    private HttpSession session;
    @GetMapping
    public String getImportProduct(Model model){
        model.addAttribute("listProductsDetails",importProductService.findAll());
        return "admin/importProduct/importProduct";
    }
    @GetMapping("/add")
    public String getImportProductDtails(Model model){
        model.addAttribute("listProductsDetails",product_detailsService.findAll());
        return "admin/importProduct/addImportProduct";
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getImportProductId( @PathVariable int id){
        List<ImportProductDetailsDto> list = iImportProductDetailsService.findAllById(id);
        if(list.isEmpty()){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(list);
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addImportProduct(@RequestBody ImportProductDto importProductDto){
         List<Products_detailsDto> detailsDtoList = importProductDto.getProductsDetailsDtoList();
         if(detailsDtoList.isEmpty()){
             return ResponseEntity.ok(false);
         }
         ImportProductDto importProductDto1 = new ImportProductDto();
         // dong này rồi sửa thành staff hiện tại
        StaffDto staffDto = (StaffDto) session.getAttribute("user");
         importProductDto1.setStaff(staffDto);
         importProductDto1 = importProductService.saveAndFlush(importProductDto1);
         for (Products_detailsDto x : detailsDtoList){
             ImportProductDetailsDto importProductDetails  = new ImportProductDetailsDto();
             importProductDetails.setImportProduct(importProductDto1);
             importProductDetails.setQuantity(x.getQuantity());
             importProductDetails.setProducts_details(x);
             iImportProductDetailsService.saveAndFlush(importProductDetails);
             Products_detailsDto products_detailsDto = product_detailsService.getById(x.getId());
             products_detailsDto.setQuantity(products_detailsDto.getQuantity()+x.getQuantity());
             product_detailsService.saveAndFlush(products_detailsDto);
         }

        return ResponseEntity.ok(true);
    }
}
