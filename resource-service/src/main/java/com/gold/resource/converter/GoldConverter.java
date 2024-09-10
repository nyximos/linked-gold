package com.gold.resource.converter;

import com.gold.resource.persistence.repository.entity.GoldEntity;
import com.gold.resource.service.domain.Gold;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoldConverter {

    Gold convert(GoldEntity source);

}
