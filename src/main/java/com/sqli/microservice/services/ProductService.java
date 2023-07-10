package com.sqli.microservice.services;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.microservice.entities.Product;
import com.sqli.microservice.entities.ProductCategory;
import com.sqli.microservice.repositories.ProductCategoryRepository;
import com.sqli.microservice.repositories.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    
    final ZonedDateTime now = ZonedDateTime.now();
    
    @Autowired
    public ProductService(ProductRepository productRepository ,ProductCategoryRepository productCategoryRepository ) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }
    
    

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setUnitPrice(product.getUnitPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setDateCreated(product.getDateCreated());
        existingProduct.setLastUpdated(now);
        existingProduct.setActive(product.getActive());
        existingProduct.setImageUrl(product.getImageUrl());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public List<ProductCategory> getAllProductsCategory() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory getProductCategoryById(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    public ProductCategory createProductCategoryCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public List<Product> findByCategoryId() {
        return productRepository.findByCategoryId();        		
    
    }
    
    public List<Product> findByNameContaining(){
    	return productRepository.findByNameContaining();
    }

    public ProductCategory updateProductCategory(Long id, ProductCategory productCategory) {
    	ProductCategory existingProductCategory = getProductCategoryById(id);
        if (existingProductCategory == null) {
            return null;
        }
        existingProductCategory.setCategoryName(productCategory.getCategoryName());
        return productCategoryRepository.save(existingProductCategory);
    }

    public void deleteProductCategory(Long id) {
    	productCategoryRepository.deleteById(id);
    }

    @KafkaListener(topics = "product-details-request", groupId = "group-3")
    public void receiveProductDetailsRequest(Long productId) throws JsonProcessingException {
        // Retrieve the product details from the database using JPA or Hibernate
        Product product = this.getProductById(productId);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        // Send the product details back to the order service using Kafka
        kafkaTemplate.send("product-details-response", json);
    }
}
