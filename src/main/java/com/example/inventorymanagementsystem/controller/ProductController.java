package com.example.inventorymanagementsystem.controller;

import com.example.inventorymanagementsystem.model.Product;
import com.example.inventorymanagementsystem.model.Supplier;
import com.example.inventorymanagementsystem.repository.ProductRepository;
import com.example.inventorymanagementsystem.service.ProductService;
import com.example.inventorymanagementsystem.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable long id){
       return productService.getProductById(id);
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        productService.createNewProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
