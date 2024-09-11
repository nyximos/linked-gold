package com.gold.resource.persistence.repository;

import com.gold.core.config.DefaultJpaRepository;
import com.gold.resource.persistence.repository.entity.InvoiceEntity;

import java.util.Optional;

public interface InvoiceRepository extends DefaultJpaRepository<InvoiceEntity, String>, InvoiceRepositoryCustom {
    Optional<InvoiceEntity> findByIdAndCustomerId(String invoiceId, Long userId);
}
