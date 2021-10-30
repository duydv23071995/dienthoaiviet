package com.example.dienthoaiviet.controller.website;

import com.example.dienthoaiviet.dto.*;
import com.example.dienthoaiviet.service.*;
import com.example.dienthoaiviet.service.impl.CookieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin(origins = "https://sandbox.vnpayment.vn/*")
@RequestMapping("/dienthoaiviet/cart")
public class CartController {
    @Autowired
    private CookieService cookieService;
    @Autowired
    private IProduct_detailsService product_detailsService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IPropertiesService propertiesService;
    @Autowired
    private IVoucherService voucherService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private HttpSession session;
    @Autowired
    private IBillService billService;
    @Autowired
    private IBill_detailService bill_detailService;

    @ModelAttribute("categorywebstie")
    public List<CategoryDto> findAllCategory() {
        return categoryService.findAll();
    }

    @ModelAttribute("properties")
    public List<PropertiesDto> findAllProperties() {
        return propertiesService.findAll();
    }

    @ModelAttribute("listProductCart")
    public List<Products_detailsDto> findAllProductCart() {
        return cookieService.findListProductdetails();
    }

    @ModelAttribute("sumMoneyProductCart")
    public int sumProductCart() {
        int sum = 0;
        for (Products_detailsDto x : cookieService.findListProductdetails()) {
            sum += x.getQuantity() * (x.getProducts().getPrice() * (1 - x.getProducts().getDiscout() / 100.0));
        }
        return sum;
    }

    @GetMapping
    public String getCart() {
        return "website/cart/index";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addCart(@Valid @RequestBody ProductCartDto productCartDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.ok(false);
        }
        if (productCartDto.getQuantity() == 0) {
            return ResponseEntity.ok("quantity = 0");
        }
        Products_detailsDto products_details = product_detailsService.findAllById(productCartDto.getProductDetailsId());
        if (products_details.getQuantity() == 0) {
            return ResponseEntity.ok("out of product");
        } else if (productCartDto.getQuantity() > products_details.getQuantity()) {
            return ResponseEntity.ok("quantity is too");
        }
        String nameCookie = "IdProduct" + productCartDto.getProductDetailsId();
        cookieService.create(nameCookie, "" + productCartDto.getQuantity(), 30);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        List<Products_detailsDto> products_detailsList = cookieService.findListProductdetails();
        for (Products_detailsDto x : products_detailsList) {
            if (x.getId().equals(id)) {
                cookieService.create("IdProduct" + id, "" + x.getQuantity(), 0);
                products_detailsList.remove(x);
                break;
            }
        }
        return ResponseEntity.ok(products_detailsList);
    }

    @PostMapping("/editquantity")
    @ResponseBody
    public ResponseEntity<?> editProductQuantity(@RequestBody ProductCartDto productCartDto) {
        System.out.println(productCartDto);
        Products_detailsDto products_detailsDto = product_detailsService.findAllById(productCartDto.getProductDetailsId());
        if (products_detailsDto.getQuantity() < productCartDto.getQuantity()) {
            cookieService.create("IdProduct" + productCartDto.getProductDetailsId(), "" + products_detailsDto.getQuantity(), 30);
            return ResponseEntity.ok(false);
        }
        cookieService.create("IdProduct" + productCartDto.getProductDetailsId(), "" + productCartDto.getQuantity(), 30);
        List<Products_detailsDto> products_detailsList = cookieService.findListProductdetails();
        for (Products_detailsDto x : products_detailsList) {
            if (x.getId().equals(productCartDto.getProductDetailsId())) {
                x.setQuantity(productCartDto.getQuantity());
                break;
            }
        }

        return ResponseEntity.ok(products_detailsList);
    }

    @PostMapping("/voucher/{code}")
    @ResponseBody
    public ResponseEntity<?> addVoucher(@PathVariable String code) {
        VoucherDto voucherDto = voucherService.findAllByCode(code);
        if (voucherDto == null) {
            return ResponseEntity.ok(false);
        }else {
            long start =(new Date().getTime()-voucherDto.getStartDay().getTime())/1000;
            long end =(new Date().getTime()-voucherDto.getEndDay().getTime())/1000;
            if(start<0 || end>0){
                return ResponseEntity.ok(false);
            }
        }
        return ResponseEntity.ok(voucherDto);
    }

    @PostMapping("/checkout")
    @ResponseBody
    public ResponseEntity<?> checkOut(@RequestBody BillDto billDto) throws IOException {
        CustomerDto customerDto = customerService.findByPhoneNumber(billDto.getCustomer().getPhoneNumber());
        CustomerDto customerDto1 = new CustomerDto();
        if (customerDto == null) {
            customerDto1.setPhoneNumber(billDto.getCustomer().getPhoneNumber());
            customerDto1.setFullName(billDto.getCustomer().getFullName());
            customerDto1.setAddress(billDto.getCustomer().getAddress());
            customerDto1.setEmail(billDto.getCustomer().getEmail());
            customerDto1.setStatus(true);
          customerDto1=  customerService.saveAndFlush(customerDto1);
        } else {
            customerDto1 = customerDto;
        }
        if (billDto.getPayment() == 2) {
            ObjectMapper modelMapper = new ObjectMapper();
            String a = payments(billDto.getSumMoney());
            CartDto cartDto = modelMapper.readValue(a, CartDto.class);
            session.setAttribute("customer", customerDto1);
            session.setAttribute("billDto",billDto);
            return ResponseEntity.ok(cartDto);
        } else {
            VoucherDto voucherDto = voucherService.findAllByCode(billDto.getCode_Customer());
            if(voucherDto!=null){
                voucherDto.setQuantity(voucherDto.getQuantity()-1);
                voucherService.saveAndFlush(voucherDto);
            }
            billDto.setCustomer(customerDto1);
            billDto.setVoucher(voucherDto);
            billDto.setStatus(0);
            billDto = billService.saveAndFlush(billDto);
            List<Products_detailsDto> productsDetailsDtoList = cookieService.findListProductdetails();
            for (Products_detailsDto x : productsDetailsDtoList) {
                Bill_detailsDto bill_detailsDto = new Bill_detailsDto();
                bill_detailsDto.setBill(billDto);
                bill_detailsDto.setQuantity(x.getQuantity());
                bill_detailsDto.setProducts_details(x);
                bill_detailService.saveAndFlush(bill_detailsDto);
                String cookie = "IdProduct" + x.getId();
                cookieService.create(cookie, "" + x.getQuantity(), 0);

            }
            session.removeAttribute("customer");
            return ResponseEntity.ok("Order Success");
        }
    }

    @GetMapping("/checkout")
    public String addCheckout() {
        String code = request.getParameter("vnp_ResponseCode");
        if(code!=null && code.equals("00")){
            CustomerDto customerDto = (CustomerDto) session.getAttribute("customer");
            BillDto billDto = (BillDto) session.getAttribute("billDto");
            VoucherDto voucherDto = voucherService.findAllByCode(billDto.getCode_Customer());
            if(voucherDto!=null){
                voucherDto.setQuantity(voucherDto.getQuantity()-1);
                voucherService.saveAndFlush(voucherDto);
            }
            billDto.setCustomer(customerDto);
            billDto.setVoucher(voucherDto);
            billDto.setStatus(1);
            billDto = billService.saveAndFlush(billDto);
            List<Products_detailsDto> productsDetailsDtoList = cookieService.findListProductdetails();
            for (Products_detailsDto x : productsDetailsDtoList) {
                Bill_detailsDto bill_detailsDto = new Bill_detailsDto();
                bill_detailsDto.setBill(billDto);
                bill_detailsDto.setQuantity(x.getQuantity());
                bill_detailsDto.setProducts_details(x);
                bill_detailService.saveAndFlush(bill_detailsDto);
                String cookie = "IdProduct" + x.getId();
                cookieService.create(cookie, "" + x.getQuantity(), 0);
            }
            return "redirect:/dienthoaiviet";
        }else {
            return "website/cart/index";
        }
    }
    @GetMapping("/history/{phone}")
    public String getHistory(@PathVariable String phone, Model model){
        List<BillDto> billDtos = billService.findAllBillCustomer(phone);
        if(billDtos.isEmpty()){
            return "redirect:/dienthoaiviet";
        }
        model.addAttribute("listHistory",billDtos);
        return "website/history/history";
    }
    @GetMapping("checkPhone/{phone}")
    @ResponseBody
    public ResponseEntity<?> checkPhone(@PathVariable String phone){
        List<BillDto> billDtos = billService.findAllBillCustomer(phone);
        if (billDtos.isEmpty()){
            return ResponseEntity.ok("no phone");
        }
         return ResponseEntity.ok(true);
    }
    public String payments(int SumMoney) throws IOException {
        String vnp_Version = "2.0.0";
        String vnp_Command = "pay";
        String vnp_OrderInfo = "Thanh toan don hang test";
        String orderType = "billpayment";
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(request);

        String vnp_TmnCode = Config.vnp_TmnCode;

        String vnp_TransactionNo = vnp_TxnRef;
        String vnp_hashSecret = Config.vnp_HashSecret;

        int amount = SumMoney * 100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        String bank_code = "";
        if (bank_code != null && bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vi";
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Date dt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(dt);
        String vnp_CreateDate = dateString;
        String vnp_TransDate = vnp_CreateDate;
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(fieldValue);
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.Sha256(Config.vnp_HashSecret + hashData.toString());
        //System.out.println("HashData=" + hashData.toString());
        queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        com.google.gson.JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", paymentUrl);
        Gson gson = new Gson();
        request.setAttribute("code", "00");
        request.setAttribute("message", "success");
        request.setAttribute("data", paymentUrl);
        return gson.toJson(job);
//        response.sendRedirect(paymentUrl);
    }
}
