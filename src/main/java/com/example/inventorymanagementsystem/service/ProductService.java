package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.exception.DuplicateProductException;
import com.example.inventorymanagementsystem.exception.ProductNotFoundException;
import com.example.inventorymanagementsystem.model.Product;
import com.example.inventorymanagementsystem.model.Supplier;
import com.example.inventorymanagementsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // CRUD Operations
    public Product createNewProduct(Product product) {
        Optional<Product> newProduct = productRepository.getByNameAndSupplier_Id(product.getName(), product.getSupplier().getId());

        if (newProduct.isPresent()) {
            throw new DuplicateProductException("Product with name " + product.getName() + " already exists. Use updateProduct() for these purposes.");
        }

        return productRepository.save(product);
    }

    // TO DO: either on the GUI part when selected an item you can increase the quantity and this method is okay
    // or you can have as parameter the product with the new quantity
    // or have the old product with the quantity as below
    public Product updateProduct(long id, Product product) {
        Optional<Product> existingProduct =  productRepository.findById(id);

        if (existingProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with name " + product.getName() + " does not exist.");
        }

        Product updatedProduct = existingProduct.get();

        updatedProduct.setName(product.getName());
        updatedProduct.setSupplier(product.getSupplier());
        updatedProduct.setQuantity(product.getQuantity());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setLowStockThreshold(product.getLowStockThreshold());

        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(long id) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isEmpty()) {
            throw new ProductNotFoundException("This product does not exist.");
        }

        productRepository.delete(existingProduct.get());
    }


    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    // Stock management
    public void increaseProductQuantity(long id, int quantity) {
        Optional<Product> temp = productRepository.findById(id);
        if (temp.isEmpty()) {
            throw new ProductNotFoundException("This product does not exist.");
        }
        temp.get().setQuantity(temp.get().getQuantity() + quantity);
        productRepository.save(temp.get());
    }

    public void decreaseProductQuantity(long id, int quantity) {
        Optional<Product> temp = productRepository.findById(id);
        if (temp.isEmpty()) {
            throw new ProductNotFoundException("This product does not exist.");
        }
        temp.get().setQuantity(temp.get().getQuantity() - quantity);
        productRepository.save(temp.get());
    }

    public void updateProductQuantity(long id, int newQuantity) {
        Optional<Product> temp = productRepository.findById(id);
        if (temp.isEmpty()) {
            throw new ProductNotFoundException("This product does not exist.");
        }
        temp.get().setQuantity(newQuantity);
        productRepository.save(temp.get());
    }

    public List<Product> getLowStockProducts() {
        return productRepository.findAll().stream().filter(product -> product.getQuantity() < product.getLowStockThreshold()).collect(Collectors.toList());
    }

    // Search and Filtering
    public List<Product> findBySupplier(Supplier supplier) {
        return productRepository.findAllBySupplier_Id(supplier.getId());
    }

    public List<Product> findByStockRange(int low, int high) {
        return productRepository.findByQuantityBetween(low, high);
    }


//
//    public Product addProduct(Product product) {
//       if(productRepository.existsByNameAndSupplier_Id(product.getName(),  product.getSupplier().getId())) {
//          Product existingProduct = productRepository.getByNameAndSupplier_Id(product.getName(),  product.getSupplier().getId());
//          existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
//          return productRepository.save(existingProduct);
//       }
//       return productRepository.save(product);
//    }
}
