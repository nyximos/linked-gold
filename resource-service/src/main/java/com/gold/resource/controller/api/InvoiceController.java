package com.gold.resource.controller.api;

import com.gold.core.wrapper.ResultResponse;
import com.gold.core.wrapper.TokenUser;
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
    public ResultResponse<Void> test(@RequestAttribute(value = TOKEN_USER) TokenUser tokenUser,
                                     @PathVariable(value = "id") Long goldId,
                                     @RequestParam(value = "unit") BigDecimal unit) {
//        invoiceService.
        return new ResultResponse<>();
    }



}
