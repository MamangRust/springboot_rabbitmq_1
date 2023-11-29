package com.sanedge.product_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.product_service.model.Product;
import com.sanedge.product_service.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setQuantity(productDetails.getQuantity());
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void reduceProductQuantity(Long productId, int quantity) {
        Product product = getProductById(productId);
        if (product != null) {
            int updatedQty = product.getQuantity() - quantity;
            if (updatedQty >= 0) {
                product.setQuantity(updatedQty);
                productRepository.save(product);
                System.out.println("Quantity reduced for product: " + productId + " new quantity: " + updatedQty);
            } else {
                System.out.println("Not enough quantity for product: " + productId);
            }
        }
    }
}
