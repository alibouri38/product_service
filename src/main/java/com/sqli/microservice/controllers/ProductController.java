package com.sqli.microservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.microservice.entities.Product;
import com.sqli.microservice.entities.ProductCategory;
import com.sqli.microservice.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
   
    @GetMapping(path = "", produces = {
    		  MediaTypes.HAL_JSON_VALUE,
    		  MediaType.APPLICATION_JSON_VALUE,
    		  MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE
    		})
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
        try {
        	System.out.println("+++product-category+++");
			return productService.getAllProductsCategory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    @GetMapping("/search/findByCategoryId")
    public List<Product> findByCategoryId() {
        try {
        	System.out.println("+++findByCategoryId+++");
			return productService.findByCategoryId();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    @GetMapping("/search/findByNameContaining")
    public List<Product> findByNameContaining() {
        try {
        	System.out.println("+++findByNameContaining+++");
			return productService.findByNameContaining();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
    }
    
    
  
}
