package com.shrooms.scaffold.service.user;

import com.shrooms.scaffold.mapper.user.UserMapper;
import com.shrooms.scaffold.model.dto.user.UserDto;
import com.shrooms.scaffold.model.dto.user.UserRegisterRequest;
import com.shrooms.scaffold.model.entity.user.User;
import com.shrooms.scaffold.repository.user.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto register(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByUsername(userRegisterRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (!userRegisterRequest.getPassword()
                .equals(userRegisterRequest.getConfirmPassword())) {
            throw new RuntimeException("Passwords don't match");
        }
        User user = UserMapper.toUserEntity(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        User savedUser = userRepository.save(user);

        return UserMapper.toUserDto(savedUser);

    }

}
