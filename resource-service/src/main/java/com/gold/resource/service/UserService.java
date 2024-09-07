package com.gold.resource.service;

import com.gold.resource.controller.model.request.SignUpRequestModel;
import com.gold.resource.converter.UserConverter;
import com.gold.resource.persistence.repository.UserRepository;
import com.gold.resource.persistence.repository.entity.UserEntity;
import com.gold.resource.service.delegator.validate.DuplicateEmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DuplicateEmailValidator duplicateEmailValidator;
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequestModel signUpRequestModel) {
        duplicateEmailValidator.validate(signUpRequestModel.getEmail());
        UserEntity user = userConverter.convert(signUpRequestModel);
        user.updatePassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
