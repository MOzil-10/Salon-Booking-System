package com.Salon.Booking.Services.Customer;

import com.Salon.Booking.DTO.ProductDto;

import java.util.List;

public interface CustomerService {

    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
}
