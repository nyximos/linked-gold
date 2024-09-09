package com.gold.auth.persistence.repository;

import com.gold.auth.persistence.repository.entity.RefreshTokenEntity;
import com.gold.core.config.DefaultRedisRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends DefaultRedisRepository<RefreshTokenEntity, String> {
}
