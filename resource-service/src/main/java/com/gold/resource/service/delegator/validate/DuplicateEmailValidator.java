package com.gold.resource.service.delegator.validate;

import com.gold.core.exception.EmailAlreadyInUseException;
import com.gold.resource.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuplicateEmailValidator {

    private final UserRepository userRepository;

    public void validate(String email) {
        userRepository.findByUsername(email).ifPresent(o -> {throw new EmailAlreadyInUseException();});
    }
}
