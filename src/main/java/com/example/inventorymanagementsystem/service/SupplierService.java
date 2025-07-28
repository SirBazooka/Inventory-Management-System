package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.exception.SupplierNotFoundException;
import com.example.inventorymanagementsystem.model.Supplier;
import com.example.inventorymanagementsystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // CRUD Operations
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(long id) {
        return supplierRepository.findById(id);
    }

    public Supplier createNewSupplier(Supplier supplier) {
        Optional<Supplier> optional = supplierRepository.findById(supplier.getId());

        if (optional.isPresent()) {
            throw new IllegalArgumentException("Supplier with id " + supplier.getId() + " already exists");
        }

        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(long id, Supplier supplier) {
        Optional<Supplier> optional = supplierRepository.findById(id);

        if(optional.isEmpty()) {
            throw new SupplierNotFoundException("Supplier with id " + id + " not found");
        }

        Supplier supplierToUpdate = optional.get();

        supplierToUpdate.setName(supplier.getName());
        supplierToUpdate.setEmail(supplier.getEmail());
        supplierToUpdate.setPhone(supplier.getPhone());

        return supplierRepository.save(supplierToUpdate);
    }

    public void deleteSupplier(long id){
        Optional<Supplier> optional = supplierRepository.findById(id);

        if(optional.isEmpty()){
            throw new SupplierNotFoundException("Supplier with id " + id + " not found");
        }

        supplierRepository.deleteById(id);
    }
}
