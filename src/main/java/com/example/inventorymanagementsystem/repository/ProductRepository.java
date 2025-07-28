package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getByNameAndSupplier_Id(String name, long supplierId);
    boolean existsByNameAndSupplier_Id(String name, long supplierId);

    List<Product> findAllBySupplier_Id(long supplierId);

    List<Product> findByQuantityBetween(int quantityAfter, int quantityBefore);

}
