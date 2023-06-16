package com.jibarrad.market.persistence;

import com.jibarrad.market.domain.ProductDomain;
import com.jibarrad.market.persistence.crud.ProductCrudRepository;
import com.jibarrad.market.persistence.entity.Product;
import com.jibarrad.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements com.jibarrad.market.domain.repository.ProductRepository {
    private ProductCrudRepository productCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<ProductDomain> getAll() {
        List<Product> products = (List<Product>) productCrudRepository.findAll();
        return mapper.toProducts(products);
    }

    @Override
    public Optional<List<ProductDomain>> getByCategory(int idCategory) {
        List<Product> products = productCrudRepository.findByIdCategoryOrderByNameAsc(idCategory);
        return Optional.of(mapper.toProducts(products));
    }

    @Override
    public Optional<List<ProductDomain>> getScarseProducts(int quantity) {
        Optional<List<Product>> products = productCrudRepository.findByStockQuantityLessThanAndState(quantity, true);
        return products.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<ProductDomain> getProduct(int idProduct) {
        return productCrudRepository.findById(idProduct).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public ProductDomain save(ProductDomain productDomain) {
        Product product = mapper.toProduct(productDomain);
        return mapper.toProduct(productCrudRepository.save(product));
    }

    @Override
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }
}
