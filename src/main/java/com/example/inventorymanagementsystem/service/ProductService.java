package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.model.Product;
import com.example.inventorymanagementsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createNewProduct(Product product)  {
       if(productRepository.existsByNameAndSupplier_Id(product.getName(), product.getSupplier().getId())) {
           throw new IllegalStateException("Product with name " + product.getName() + " already exists. Use updateProductQuantity() for these purposes.");
       }
       return productRepository.save(product);
    }

    // TO DO: either on the GUI part when selected an item you can increase the quantity and this method is okay
    // or you can have as parameter the product with the new quantity
    // or have the old product with the quantity as below
    public Product updateProductQuantity(Product product, int quantity) {
        Product existingProduct = productRepository.getByNameAndSupplier_Id(product.getName(), product.getSupplier().getId());
        existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        existingProduct.setQuantity(quantity);
        return productRepository.save(existingProduct);
    }



    public Product addProduct(Product product) {
       if(productRepository.existsByNameAndSupplier_Id(product.getName(),  product.getSupplier().getId())) {
          Product existingProduct = productRepository.getByNameAndSupplier_Id(product.getName(),  product.getSupplier().getId());
          existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
          return productRepository.save(existingProduct);
       }
       return productRepository.save(product);
    }
}
