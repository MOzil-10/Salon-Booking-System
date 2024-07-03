package com.Salon.Booking.Services.User;

import com.Salon.Booking.DTO.ProductDto;
import com.Salon.Booking.Entity.Product;
import com.Salon.Booking.Entity.User;
import com.Salon.Booking.Repository.ProductRepository;
import com.Salon.Booking.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ProductRepository productRepository;

    @Override
    public boolean addProduct(Long userId, ProductDto productDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Product product = new Product();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setImg(productDto.getImg());
            product.setUser(user);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(Product::toDto).orElse(null);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(Product::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setImg(productDto.getImg());
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
