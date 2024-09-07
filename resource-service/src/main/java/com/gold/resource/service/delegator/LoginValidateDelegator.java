package com.gold.resource.service.delegator;

import com.gold.resource.controller.model.request.LoginRequestModel;
import com.gold.resource.service.delegator.validate.LoginValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginValidateDelegator {

    private final List<LoginValidator> validators;

    public void validate(LoginRequestModel target) {
        validators.stream().forEach(x -> x.validate(target));
    }
}
