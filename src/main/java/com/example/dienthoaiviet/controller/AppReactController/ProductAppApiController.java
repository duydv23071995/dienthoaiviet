//package com.example.dienthoaiviet.controller.AppReactController;
//
//import com.example.dienthoaiviet.dto.ProductsDto;
//import com.example.dienthoaiviet.dto.Products_detailsDto;
//import com.example.dienthoaiviet.service.IImageService;
//import com.example.dienthoaiviet.service.IProduct_detailsService;
//import com.example.dienthoaiviet.service.IProductservice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Description:
// *
// * @author: GMO_DuyDV
// * @version: 1.0
// * @since: 12/13/2021
// * Project_name: GMO_QuanLyTaiSan
// */
//@RestController
//@CrossOrigin("http://localhost:3000/")
//@RequestMapping("/didongviet")
//public class ProductAppApiController {
//    @Autowired
//    private IProductservice productservice;
//    @Autowired
//    private IProduct_detailsService product_detailsService;
//    @Autowired
//    private IImageService iImageService;
//
//    @GetMapping("/product/{id}")
//    public ResponseEntity<?> findSingleProduct(@PathVariable String id){
//        System.out.println(id);
//        ProductsDto productsDto = productservice.findAllById(id);
//        if(productsDto==null){
//            return ResponseEntity.ok("same no product");
//        }else {
//            return ResponseEntity.ok(productsDto);
//        }
//    }
//    @GetMapping("/productdetails/{id}")
//    public ResponseEntity<?> findSingleProductDetails(@PathVariable String id){
//        System.out.println(id);
//        List<Products_detailsDto> productsDto = product_detailsService.findAllByProductId(id);
//        if(productsDto==null){
//            return ResponseEntity.ok("same no product");
//        }else {
//            return ResponseEntity.ok(productsDto);
//        }
//    }
//}
