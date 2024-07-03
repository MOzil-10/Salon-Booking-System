package com.Salon.Booking.Controller;

import com.Salon.Booking.DTO.ProductDto;
import com.Salon.Booking.Services.User.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestParam Long userId, @RequestBody ProductDto productDto) {
        boolean isAdded = adminService.addProduct(userId, productDto);
        if (isAdded) {
            return ResponseEntity.ok("Product added successfully");
        }
        return ResponseEntity.badRequest().body("Failed to add product");
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDto = adminService.getProductById(id);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = adminService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        boolean isUpdated = adminService.updateProduct(id, productDto);
        if (isUpdated) {
            return ResponseEntity.ok("Product updated successfully");
        }
        return ResponseEntity.badRequest().body("Failed to update product");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = adminService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.badRequest().body("Failed to delete product");
    }
}
