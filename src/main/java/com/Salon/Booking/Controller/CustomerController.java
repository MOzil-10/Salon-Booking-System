package com.Salon.Booking.Controller;

import com.Salon.Booking.DTO.ProductDto;
import com.Salon.Booking.Services.Customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return customerService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return customerService.getProductById(id);
    }
}
