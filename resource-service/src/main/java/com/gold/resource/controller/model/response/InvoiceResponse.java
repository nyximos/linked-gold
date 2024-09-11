package com.gold.resource.controller.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gold.core.code.InvoiceType;
import com.gold.core.code.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.gold.core.constant.DateConst.LOCAL_DATE_TIME_FORMAT;

@Getter
@Setter
public class InvoiceResponse {

    @Schema(description = "주문 ID", example = "INV-12345")
    private String id;

    @Schema(description = "주문 타입", example = "PURCHASE")
    private InvoiceType invoiceType;

    @Schema(description = "총 금액", example = "1000.00")
    private BigDecimal amount;

    @Schema(description = "1g당 가격", example = "50.00")
    private BigDecimal unitPrice;

    @Schema(description = "무게", example = "20.00")
    private BigDecimal weight;

    @Schema(description = "주문 상태", example = "ORDER_COMPLETE")
    private OrderStatus orderStatus;

    @Schema(description = "고객 이메일", example = "test@google.com")
    private String customerEmail;

    @Schema(description = "금 ID", example = "1")
    private Long goldId;

    @Schema(description = "금 유형", example = "99.9% 금")
    private String goldType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATE_TIME_FORMAT)
    @Schema(description = "생성 일시", example = "2024-09-11T20:56:30.70")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATE_TIME_FORMAT)
    @Schema(description = "수정 일시", example = "2024-09-11T20:56:30.70")
    private LocalDateTime updatedAt;

}
