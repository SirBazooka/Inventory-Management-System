package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
