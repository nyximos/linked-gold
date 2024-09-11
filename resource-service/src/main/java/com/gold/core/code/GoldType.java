package com.gold.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoldType {

    GOLD_999("99.9% 금"),
    GOLD_9999("99.99% 금")
    ;

    private String description;

}
