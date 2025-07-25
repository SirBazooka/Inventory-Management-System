package com.example.inventorymanagementsystem.model;

public class ProductUserDTO {
    private String name;
    private int quantity;
    private double price;
    private Supplier supplier;

    public ProductUserDTO(Product product) {
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.supplier = product.getSupplier();
    }
}

