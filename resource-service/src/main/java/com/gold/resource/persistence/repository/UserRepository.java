package com.gold.resource.persistence.repository;

import com.gold.core.config.DefaultJpaRepository;
import com.gold.resource.persistence.repository.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends DefaultJpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String email);
}
