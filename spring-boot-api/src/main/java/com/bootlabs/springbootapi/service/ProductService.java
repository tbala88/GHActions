package com.bootlabs.springbootapi.service;

import com.bootlabs.springbootapi.dto.CreateProductRequest;
import com.bootlabs.springbootapi.dto.ProductResponse;
import com.bootlabs.springbootapi.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final Map<Long, ProductResponse> products = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();
    private final String podName = System.getenv().getOrDefault("POD_NAME", "local");

    public ProductService() {
        save("Kubernetes in Action", 39.99);
        save("Spring Boot: Up and Running", 34.50);
        save("Site Reliability Engineering", 29.90);
    }

    public List<ProductResponse> findAll() {
        return List.copyOf(products.values());
    }

    public ProductResponse findById(Long id) {
        ProductResponse product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    public ProductResponse create(CreateProductRequest request) {
        return save(request.name(), request.price());
    }

    private ProductResponse save(String name, double price) {
        long id = sequence.incrementAndGet();
        ProductResponse product = new ProductResponse(id, name, price, podName);
        products.put(id, product);
        return product;
    }
}
