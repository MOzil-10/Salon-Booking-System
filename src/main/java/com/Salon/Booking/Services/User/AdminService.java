package com.Salon.Booking.Services.User;

import com.Salon.Booking.DTO.ProductDto;

import java.util.List;

public interface AdminService {

    boolean addProduct(Long userId, ProductDto productDto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    boolean updateProduct(Long id, ProductDto productDto);
    boolean deleteProduct(Long id);
}
