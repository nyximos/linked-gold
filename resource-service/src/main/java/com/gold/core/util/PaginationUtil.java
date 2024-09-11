package com.gold.core.util;

import com.gold.core.code.InvoiceSearchType;
import com.gold.core.wrapper.LinksResponse;
import com.gold.resource.controller.model.request.InvoiceSearchRequest;
import com.gold.resource.controller.model.response.InvoiceListResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public class PaginationUtil {
    public static LinksResponse links(List<InvoiceListResponse> invoices, String baseUrl, InvoiceSearchRequest request, long totalElements) {
        int currentOffset = request.getOffset();
        int limit = request.getLimit();
        int nextOffset = currentOffset + limit;
        int prevOffset = Math.max(0, currentOffset - limit);

        String selfUrl = buildUrl(baseUrl, request.getDate(), limit, currentOffset, request.getInvoiceSearchType());
        String nextUrl = invoices.size() == limit ? buildUrl(baseUrl, request.getDate(), limit, nextOffset, request.getInvoiceSearchType()) : null;
        String prevUrl = currentOffset > 0 ? buildUrl(baseUrl, request.getDate(), limit, prevOffset, request.getInvoiceSearchType()) : null;
        String firstUrl = buildUrl(baseUrl, request.getDate(), limit, 0, request.getInvoiceSearchType());
        String lastUrl = buildUrl(baseUrl, request.getDate(), limit, (int)((totalElements - 1) / limit) * limit, request.getInvoiceSearchType());

        return new LinksResponse(selfUrl, nextUrl, prevUrl, firstUrl, lastUrl);
    }


    public static String buildUrl(String baseUrl, LocalDate date, int limit, int offset, InvoiceSearchType invoiceSearchType) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);
        sb.append("?date=");
        sb.append(date);
        sb.append("&limit=");
        sb.append(limit);
        sb.append("&offset=");
        sb.append(offset);
        sb.append("&invoiceSearchType=");
        sb.append(invoiceSearchType);
        return sb.toString();
    }

    public static String getBaseUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }
}