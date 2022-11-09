package com.webshoppe.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import com.webshoppe.ecommerce.entity.Product;
import com.webshoppe.ecommerce.entity.Toy;
import com.webshoppe.ecommerce.exception.DataAccessException;
import com.webshoppe.ecommerce.exception.ServiceException;
import com.webshoppe.ecommerce.repository.ProductRepository;
import com.webshoppe.ecommerce.repository.ToyRepository;

public class ProductCatalogService {
    private ProductRepository productRepository;

    public ProductCatalogService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductCatalog() {
        try {
            return productRepository.findAll();
        } catch (DataAccessException e) {
            throw ServiceException.instance(e.getMessage());
        }

    }

    public List<Product> getProductCatalog(BigDecimal minimumPrice, BigDecimal maximumPrice) {
        try {
            return productRepository.findByPrice(minimumPrice, maximumPrice);
        } catch (DataAccessException e) {
            throw ServiceException.instance(e.getMessage());
        }

    }
}
