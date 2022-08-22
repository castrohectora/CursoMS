package com.hectorc.userservice.feingclients;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class FeingInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders httpHeaders = new HttpHeaders();

        template.header("Authorization", "Bearer " + jwt.getTokenValue());
    }
}
