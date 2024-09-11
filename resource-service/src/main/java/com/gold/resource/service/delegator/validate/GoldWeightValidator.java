package com.gold.resource.service.delegator.validate;

import com.gold.core.exception.InsufficientWeightException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class GoldWeightValidator {

    public void validateWeight(BigDecimal goldWeight, BigDecimal requestedWeight) {
        if (goldWeight.compareTo(requestedWeight) < 0) {
            throw new InsufficientWeightException();
        }
    }
}
