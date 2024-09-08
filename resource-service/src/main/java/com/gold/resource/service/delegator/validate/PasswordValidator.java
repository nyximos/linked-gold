package com.gold.resource.service.delegator.validate;

import com.gold.core.exception.InvalidPasswordException;
import com.gold.core.exception.UserNotFoundException;
import com.gold.resource.controller.model.request.LoginRequestModel;
import com.gold.resource.persistence.repository.UserRepository;
import com.gold.resource.persistence.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordValidator implements LoginValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void validate(LoginRequestModel target) {
        UserEntity user = userRepository.findByUsername(target.getEmail()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(target.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }
    }
}
