package com.example.inventorymanagementsystem.component;

import com.example.inventorymanagementsystem.model.Product;
import com.example.inventorymanagementsystem.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LowStockScheduler {

    private final ProductService productService;

    public LowStockScheduler(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void scheduledLowStock() {
        List<Product> products =  productService.getLowStockProducts();
        if(products != null) {
            System.out.println("Alert!");
        }
    }
}
