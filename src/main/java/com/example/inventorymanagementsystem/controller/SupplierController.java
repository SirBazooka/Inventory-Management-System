package com.example.inventorymanagementsystem.controller;

import com.example.inventorymanagementsystem.model.Supplier;
import com.example.inventorymanagementsystem.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    // this comment just for testing

    @GetMapping
    public List<Supplier> getAllSuppliers() {
       return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
       supplierService.createNewSupplier(supplier);
       return ResponseEntity.status(HttpStatus.CREATED).body(supplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable long id, @Valid @RequestBody Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierService.getSupplierById(id);
        if (optionalSupplier.isEmpty()) {
           return ResponseEntity.notFound().build();
        }
        Supplier s = supplierService.updateSupplier(id,  supplier);
        return ResponseEntity.ok().body(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable long id) {
        Optional<Supplier> optionalSupplier = supplierService.getSupplierById(id);

        if(optionalSupplier.isEmpty()){
            ResponseEntity.notFound().build();
        }

        supplierService.deleteSupplier(id);
        return ResponseEntity.ok().body(optionalSupplier.get());
    }
}
