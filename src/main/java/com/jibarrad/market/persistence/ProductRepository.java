package com.jibarrad.market.persistence;

import com.jibarrad.market.persistence.crud.ProductCrudRepository;
import com.jibarrad.market.persistence.entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private ProductCrudRepository productCrudRepository;

    public List<Product> getAll() {
        return (List<Product>) productCrudRepository.findAll();
    }

    public List<Product> getByCategory(int idCategory) {
        return productCrudRepository.findByIdCategoryOrderByNameAsc(idCategory);
    }

    public Optional<List<Product>> getScarse(int quantity) {
        return productCrudRepository.findByStockQuantityLessThanAndState(quantity, true);
    }
}
