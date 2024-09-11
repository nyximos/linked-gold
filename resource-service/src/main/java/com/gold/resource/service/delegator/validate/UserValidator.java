package com.gold.resource.service.delegator.validate;

import com.gold.core.exception.UserNotFoundException;
import com.gold.resource.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validate(Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
