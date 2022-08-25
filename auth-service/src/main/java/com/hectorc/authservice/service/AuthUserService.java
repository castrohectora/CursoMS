package com.hectorc.authservice.service;

import com.hectorc.authservice.dto.AuthUserDto;
import com.hectorc.authservice.dto.TokenDto;
import com.hectorc.authservice.entity.AuthUser;
import com.hectorc.authservice.repository.AuthUserRepository;
import com.hectorc.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public AuthUser save(AuthUserDto dto) {
        Optional<AuthUser> user = userRepository.findByUserName(dto.getUserName());

        if (user.isPresent()) {
            return null;
        }

        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(dto.getUserName())
                .password(password)
                .build();

        return userRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto) {
        Optional<AuthUser> user = userRepository.findByUserName(dto.getUserName());

        if (!user.isPresent()) {
            return null;
        }

        if (passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            return new TokenDto(jwtProvider.getToken(user.get()));
        }

        return null;
    }

    public TokenDto validate(String token) {
        if (!jwtProvider.validate(token)) {
            return null;
        }

        String username = jwtProvider.getUserNameFromToken(token);

        if (!userRepository.findByUserName(username).isPresent()) {
            return null;
        }

        return new TokenDto(token);
    }
}
