package com.ecommerceCatalog.ecommerceCatalog.service;

import com.ecommerceCatalog.ecommerceCatalog.model.entities.Product;
import com.ecommerceCatalog.ecommerceCatalog.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> createProduct(Product product) {
        System.out.println(product);
        productRepository.save(product);
        return Optional.of(product);
    }

    public Optional<Product> getProduct(String productId){
        return productRepository.getProduct(productId);
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

}
