package com.pl.computer_store_app.user;

import com.pl.computer_store_app.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDtoMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Nie można zanaleźć użytkownika o danym adresie email"));
    }

    public boolean existUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
