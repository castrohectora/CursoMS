package com.hectorc.authservice.controller;

import com.hectorc.authservice.dto.AuthUserDto;
import com.hectorc.authservice.dto.NewUserDto;
import com.hectorc.authservice.dto.RequestDto;
import com.hectorc.authservice.dto.TokenDto;
import com.hectorc.authservice.entity.AuthUser;
import com.hectorc.authservice.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    AuthUserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto) {
        TokenDto tokenDto = userService.login(dto);

        if (tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto dto) {
        TokenDto tokenDto = userService.validate(token, dto);

        if (tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDto dto) {
        AuthUser authUser = userService.save(dto);

        if (authUser == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(authUser);
    }
}
