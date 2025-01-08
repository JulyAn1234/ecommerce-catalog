package com.ecommerceCatalog.ecommerceCatalog.model.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ecommerceCatalog.ecommerceCatalog.model.entities.Product;
import com.ecommerceCatalog.ecommerceCatalog.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class ProductRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Product save(Product product) {
        dynamoDBMapper.save(product);
        return product;
    }

    public Optional<Product> getProduct(String productId) {
        Product product = null;
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":productId", new AttributeValue().withS(productId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("productId = :productId")
                .withExpressionAttributeValues(eav);

        try {
            List<Product> useResult = dynamoDBMapper.scan(Product.class, scanExpression);
            if (!useResult.isEmpty()) {
                product = useResult.get(0);
                System.out.println("Product found: " + product.getProductId());
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.err.println("Error during DynamoDB scan:");
            e.printStackTrace(); // Print stack trace for debugging
        }

        return Optional.ofNullable(product);
    }

    public List<Product> getAllProducts(){
        return dynamoDBMapper.scan(Product.class, new DynamoDBScanExpression());
    }
}
