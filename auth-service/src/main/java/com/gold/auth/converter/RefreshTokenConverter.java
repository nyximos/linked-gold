package com.gold.auth.converter;

import com.gold.auth.persistence.repository.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenConverter {
    RefreshTokenEntity convert(String id, String refreshToken, long expiredAt);
}
