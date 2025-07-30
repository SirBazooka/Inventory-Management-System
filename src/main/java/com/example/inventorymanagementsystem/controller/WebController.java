package com.example.inventorymanagementsystem.controller;

import com.example.inventorymanagementsystem.service.ProductService;
import com.example.inventorymanagementsystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        model.addAttribute("lowStockProducts", productService.getLowStockProducts());
        return "dashboard";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "suppliers";
    }
}