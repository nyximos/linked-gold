package com.gold.resource.service.delegator.validate;

import com.gold.resource.controller.model.request.LoginRequestModel;

public interface LoginValidator {
    void validate(LoginRequestModel target);
}
