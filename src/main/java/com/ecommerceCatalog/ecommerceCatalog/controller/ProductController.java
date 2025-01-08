package com.ecommerceCatalog.ecommerceCatalog.controller;

import com.ecommerceCatalog.ecommerceCatalog.model.entities.Product;
import com.ecommerceCatalog.ecommerceCatalog.model.entities.User;
import com.ecommerceCatalog.ecommerceCatalog.service.AuthenticationService;
import com.ecommerceCatalog.ecommerceCatalog.service.JwtService;
import com.ecommerceCatalog.ecommerceCatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RequestMapping("/product")
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("Catalog Service Working Properly");
    }

    @PostMapping("")
    public ResponseEntity<Optional<Product>> createProduct(@RequestBody Product product) {
        Optional<Product> createdProduct = productService.createProduct(product);

        if (createdProduct.isEmpty()) {
            return ResponseEntity.badRequest().body(Optional.empty());
        }

        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();

        if (productList.isEmpty()) {
            return ResponseEntity.badRequest().body(productList);
        }

        return ResponseEntity.ok(productList);
    }
    @GetMapping("{productId}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable("productId") String productId) {
        Optional<Product> productFound = productService.getProduct(productId);

        if (productFound.isEmpty()) {
            return ResponseEntity.badRequest().body(Optional.empty());
        }

        return ResponseEntity.ok(productFound);
    }
}
