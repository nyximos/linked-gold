package com.gold.resource.converter;

import com.gold.resource.controller.model.response.TokenModel;
import com.nyximos.auth.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenConverter {

    TokenModel convert(Auth.TokenResponse source);
}
