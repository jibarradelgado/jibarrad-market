package com.jibarrad.market.persistence;

import com.jibarrad.market.domain.Product;
import com.jibarrad.market.persistence.crud.ProductCrudRepository;
import com.jibarrad.market.persistence.entity.ProductEntity;
import com.jibarrad.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements com.jibarrad.market.domain.repository.ProductRepository {
    @Autowired
    private ProductCrudRepository productCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<ProductEntity> productEntities = (List<ProductEntity>) productCrudRepository.findAll();
        return mapper.toProducts(productEntities);
    }

    @Override
    public Optional<List<Product>> getByCategory(int idCategory) {
        List<ProductEntity> productEntities = productCrudRepository.findByIdCategoryOrderByNameAsc(idCategory);
        return Optional.of(mapper.toProducts(productEntities));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<ProductEntity>> products = productCrudRepository.findByStockQuantityLessThanAndState(quantity, true);
        return products.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int idProduct) {
        return productCrudRepository.findById(idProduct).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = mapper.toProduct(product);
        return mapper.toProduct(productCrudRepository.save(productEntity));
    }

    @Override
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }
}
