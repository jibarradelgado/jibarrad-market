package com.jibarrad.market.persistence;

import com.jibarrad.market.domain.Purchase;
import com.jibarrad.market.persistence.crud.PurchaseCrudRepository;
import com.jibarrad.market.persistence.entity.PurchaseEntity;
import com.jibarrad.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepository implements com.jibarrad.market.domain.repository.PurchaseRepository {
    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<PurchaseEntity>) purchaseCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseCrudRepository.findByIdClient(clientId)
                .map(purchases -> mapper.toPurchases(purchases));
    }

    @Override
    public Purchase save(Purchase purchase) {
        PurchaseEntity purchaseEntity = mapper.toPurchaseEntity(purchase);
        purchaseEntity.getProductEntities().forEach(product -> product.setPurchaseEntity(purchaseEntity));
        return mapper.toPurchase(purchaseCrudRepository.save(purchaseEntity));
    }
}
