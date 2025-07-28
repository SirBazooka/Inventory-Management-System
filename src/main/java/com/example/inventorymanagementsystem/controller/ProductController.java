package com.example.inventorymanagementsystem.controller;

import com.example.inventorymanagementsystem.model.Product;
import com.example.inventorymanagementsystem.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

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
        Product p = productService.createNewProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @Valid @RequestBody Product product){
        Optional<Product> existingProduct = productService.getProductById(id);
        if(existingProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Product p = productService.updateProduct(id, product);
        return ResponseEntity.ok().body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id){
        Optional<Product> existingProduct = productService.getProductById(id);

        if(existingProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        productService.deleteProduct(id);
        return ResponseEntity.ok().body(existingProduct.get());
    }

    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts() {
        return productService.getLowStockProducts();
    }
    
}
