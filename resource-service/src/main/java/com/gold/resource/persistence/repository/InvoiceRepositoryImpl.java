package com.gold.resource.persistence.repository;

import com.gold.core.code.InvoiceSearchType;
import com.gold.core.code.InvoiceType;
import com.gold.core.wrapper.PageResponse;
import com.gold.resource.persistence.repository.entity.InvoiceEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.gold.resource.persistence.repository.entity.QInvoiceEntity.*;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public PageResponse<InvoiceEntity> findInvoices(Long userId, LocalDate date, InvoiceSearchType invoiceType, int offset, int limit) {
        List<InvoiceEntity> invoices = queryFactory.selectFrom(invoiceEntity)
                .where(invoiceEntity.customerId.eq(userId)
                        .and(filterByInvoiceDate(date))
                        .and(filterByInvoiceType(invoiceType)))
                .orderBy(invoiceEntity.createdAt.desc())
                .offset(offset)
                .limit(limit)
                .fetch();

        long total = queryFactory.select(invoiceEntity.id.count())
                .from(invoiceEntity)
                .where(invoiceEntity.customerId.eq(userId)
                        .and(filterByInvoiceDate(date))
                        .and(filterByInvoiceType(invoiceType)))
                .fetchOne();

        return new PageResponse<>(total, invoices);
    }

    private BooleanExpression filterByInvoiceDate(LocalDate date) {
        return invoiceEntity.createdAt.year().eq(date.getYear())
                .and(invoiceEntity.createdAt.month().eq(date.getMonthValue()));
    }

    private BooleanExpression filterByInvoiceType(InvoiceSearchType invoiceSearchType) {
        if (invoiceSearchType == null || InvoiceSearchType.ALL.equals(invoiceSearchType)) {
            return null;
        }
        if (InvoiceSearchType.PURCHASE.equals(invoiceSearchType)) {
            return invoiceEntity.invoiceType.eq(InvoiceType.PURCHASE);
        } else {
            return invoiceEntity.invoiceType.eq(InvoiceType.SALE);
        }
    }
}
