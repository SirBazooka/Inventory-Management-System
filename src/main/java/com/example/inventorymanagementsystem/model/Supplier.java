package com.example.inventorymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
public class Supplier implements Comparable<Supplier> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("supplier_id")
    private long id;

    @JsonProperty("supplier_name")
    @NotBlank(message = "The name of the supplier cannot be null.")
    private String name;

    @JsonProperty("supplier_phone")
    @NotBlank(message = "The phone number cannot be blank.")
    @Pattern(regexp = "^\\+?(\\d{1,3})?[-.\\s]?(\\(?\\d{3}\\)?[-.\\s]?)?(\\d[-.\\s]?){6,9}\\d$", message = "Invalid phone number.")
    private String phone;

    @JsonProperty("supplier_email")
    @Email
    private String email;

    @Override
    public int compareTo(Supplier supplier) {
        return Long.compare(this.id, supplier.id);
    }

    @Override
    public String toString() {
        return "Supplier: " + name + " ; Phone: " + phone + "; Email: " + email;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Supplier)) return false;
        if(this == other) return true;
        Supplier otherSupplier = (Supplier) other;
        return this.id == otherSupplier.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email);
    }


    public Supplier() {}

    public Supplier(long id,  String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
       return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
