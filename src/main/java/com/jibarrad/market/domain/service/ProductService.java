package com.jibarrad.market.domain.service;

import com.jibarrad.market.domain.ProductDomain;
import com.jibarrad.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDomain> getAll(){
        return productRepository.getAll();
    }

    public Optional<ProductDomain> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<ProductDomain>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public ProductDomain save(ProductDomain product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
}
