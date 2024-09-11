package com.gold.resource.controller.api;

import com.gold.core.code.OrderStatus;
import com.gold.core.wrapper.ResultResponse;
import com.gold.core.wrapper.TokenUser;
import com.gold.resource.controller.model.response.InvoiceResponse;
import com.gold.resource.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static com.gold.core.constant.ResourceConstants.TOKEN_USER;

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

    @GetMapping
    public ResultResponse<InvoiceResponse> getInvoice(@RequestAttribute(value = TOKEN_USER) TokenUser tokenUser,
                                                      @RequestParam(value = "id") String invoiceId) {
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
}
