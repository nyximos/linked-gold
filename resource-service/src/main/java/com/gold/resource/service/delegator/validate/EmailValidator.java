package com.gold.resource.service.delegator.validate;

import com.gold.core.exception.UserNotFoundException;
import com.gold.resource.controller.model.request.LoginRequestModel;
import com.gold.resource.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailValidator implements LoginValidator {

    private final UserRepository userRepository;

    @Override
    public void validate(LoginRequestModel target) {
        userRepository.findByUsername(target.getEmail()).orElseThrow(UserNotFoundException::new);
    }
}
