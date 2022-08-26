package com.hectorc.authservice.security;

import com.hectorc.authservice.dto.RequestDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDto> paths;

    public boolean isAdminPath(RequestDto request) {
        return paths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), request.getUri()) &&
                        p.getMethod().equals(request.getMethod()));
    }
}
