package com.gold.resource.converter;

import com.gold.resource.controller.model.request.SignUpRequestModel;
import com.gold.resource.persistence.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "email")
    @Mapping(target = "password", source = "password")
    UserEntity convert(SignUpRequestModel source);
}
