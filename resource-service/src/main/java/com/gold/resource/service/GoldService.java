package com.gold.resource.service;

import com.gold.core.exception.GoldNotFoundException;
import com.gold.resource.converter.GoldConverter;
import com.gold.resource.persistence.repository.GoldRepository;
import com.gold.resource.service.domain.Gold;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoldService {

    private final GoldRepository goldRepository;
    private final GoldConverter goldConverter;

    public Gold getGold(Long goldId) {
        return goldConverter.convert(goldRepository.findById(goldId).orElseThrow(GoldNotFoundException::new));
    }
}
