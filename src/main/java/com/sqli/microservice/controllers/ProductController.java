package com.sqli.microservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.sqli.microservice.entities.Product;
import com.sqli.microservice.entities.ProductCategory;
import com.sqli.microservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
    	try {
			System.out.println("@@@@@@ Product service Called @@@@@@");
			return productService.getAllProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error message +++ " + e.getMessage());
			return null;
		}
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    
    
    @GetMapping("/product-category")
    public List<ProductCategory> getAllProductsCategory() {
        return productService.getAllProductsCategory();
    }
}
