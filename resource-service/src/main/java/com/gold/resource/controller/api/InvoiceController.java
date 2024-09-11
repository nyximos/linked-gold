package com.gold.resource.controller.api;

import com.gold.core.wrapper.LinksResponse;
import com.gold.core.wrapper.PageResponse;
import com.gold.core.wrapper.ResultResponse;
import com.gold.core.wrapper.TokenUser;
import com.gold.resource.controller.model.request.InvoiceSearchRequest;
import com.gold.resource.controller.model.response.InvoiceListResponse;
import com.gold.resource.controller.model.response.InvoiceResponse;
import com.gold.resource.service.InvoiceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.gold.core.constant.ResourceConstants.TOKEN_USER;
import static com.gold.core.util.PaginationUtil.links;
import static com.gold.core.util.PaginationUtil.getBaseUrl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/{id}")
    public ResultResponse<Void> createInvoice(@RequestAttribute(value = TOKEN_USER) TokenUser tokenUser,
                                     @PathVariable(value = "id") Long goldId,
                                     @RequestParam(value = "weight") BigDecimal weight) {
        invoiceService.createInvoice(tokenUser.getId(), goldId, weight);
        return new ResultResponse<>();
    }

    @GetMapping("/{id}")
    public ResultResponse<InvoiceResponse> getInvoice(@RequestAttribute(value = TOKEN_USER) TokenUser tokenUser,
                                                      @PathVariable(value = "id") String invoiceId) {
        InvoiceResponse invoiceResponse = invoiceService.getInvoice(invoiceId, tokenUser.getId(), tokenUser.getEmail());
        return new ResultResponse<>(invoiceResponse);
    }

    @PutMapping("/payment")
    public ResultResponse<Void> payment(@RequestParam(value = "id") String invoiceId) {
        invoiceService.payment(invoiceId);
        return new ResultResponse<>();
    }

    @PutMapping("/shipment")
    public ResultResponse<Void> shipment(@RequestParam(value = "id") String invoiceId) {
        invoiceService.shipment(invoiceId);
        return new ResultResponse<>();
    }

    @PutMapping("/cancel")
    public ResultResponse<Void> cancel(@RequestParam(value = "id") String invoiceId) {
        invoiceService.cancel(invoiceId);
        return new ResultResponse<>();
    }

    @GetMapping
    public ResultResponse<List<InvoiceListResponse>> getInvoice(@RequestAttribute(value = TOKEN_USER) TokenUser tokenUser,
                                                            @ModelAttribute InvoiceSearchRequest searchRequest,
                                                            HttpServletRequest httpRequest) {
        PageResponse<InvoiceListResponse> invoices = invoiceService.getInvoices(tokenUser.getId(), searchRequest.getDate(), searchRequest.getInvoiceSearchType(), searchRequest.getOffset(), searchRequest.getLimit());
        String baseUrl = getBaseUrl(httpRequest);
        LinksResponse linksResponse = links(invoices.getContents(), baseUrl, searchRequest, invoices.getTotal());
        return new ResultResponse<>(invoices.getContents(), linksResponse);
    }

}
