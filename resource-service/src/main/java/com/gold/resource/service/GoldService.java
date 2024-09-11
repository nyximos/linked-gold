package com.gold.resource.service;

import com.gold.core.exception.GoldNotFoundException;
import com.gold.resource.converter.GoldConverter;
import com.gold.resource.persistence.repository.GoldRepository;
import com.gold.resource.persistence.repository.entity.GoldEntity;
import com.gold.resource.service.domain.Gold;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class GoldService {

    private final GoldRepository goldRepository;
    private final GoldConverter goldConverter;

    public Gold getGold(Long goldId) {
        return goldConverter.convert(goldRepository.findById(goldId).orElseThrow(GoldNotFoundException::new));
    }

    public Gold subtractWeight(Long goldId, BigDecimal weight) {
        GoldEntity goldEntity = goldRepository.findById(goldId).orElseThrow(GoldNotFoundException::new);
        goldEntity.subtractWeight(weight);
        return goldConverter.convert(goldEntity);
    }
}
