package com.gold.resource.persistence.repository;

import com.gold.core.config.DefaultJpaRepository;
import com.gold.resource.persistence.repository.entity.ProductEntity;

public interface ProductRepository extends DefaultJpaRepository<ProductEntity, Long> {
}
