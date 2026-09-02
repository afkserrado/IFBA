package br.edu.ifba.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignSecurityConfig {

    @Bean
    public RequestInterceptor bearerTokenInterceptor() {

        return template -> {

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();

            if (attributes == null) {
                return;
            }

            HttpServletRequest request = attributes.getRequest();

            String authorization = request.getHeader("Authorization");

            System.out.println(
                    "FEIGN JWT: " + authorization);
                    
            if (authorization != null) {
                template.header(
                        "Authorization",
                        authorization);
            }
        };
    }
}
