package com.Salon.Booking.Services.Customer;

import com.Salon.Booking.DTO.ProductDto;
import com.Salon.Booking.Entity.Product;
import com.Salon.Booking.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id).map(Product::toDto).orElse(null);
    }
}
