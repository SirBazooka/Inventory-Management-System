package com.example.inventorymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Product implements Comparable<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("product_id")
    private long id;

    @JsonProperty("product_name")
    @NotBlank(message = "The name of the product cannot be empty.")
    String name;

    @JsonProperty("product_quantity")
    @NotNull(message = "The quantity of the product cannot be null.")
    int quantity;

    @JsonProperty("product_price")
    @NotNull(message = "The price of the product cannot be null.")
    @DecimalMin(value = "0.0", message = "The price of the product cannot be less than 0.")
    double price;

    @JsonProperty("product_stock")
    @NotNull(message = "The threshold cannot be null.")
    @Min(value = 0, message = "The Threshold of the product cannot be less than 0.")
    int lowStockThreshold;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonProperty("product_supplier")
    Supplier supplier;

    @Override
    public int compareTo(Product other) {
        return Long.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Product: " + name + " ; Quantity: " + quantity + "; Price per unit: " + price;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Product)) return false;
        if(other == this) return true;
        Product otherProduct = (Product) other;
        return this.id == otherProduct.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price);
    }

    public Product() {}

    public Product(long id,  String name, int quantity, double price, Supplier supplier,  int lowStockThreshold) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
        this.lowStockThreshold = lowStockThreshold;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


}
