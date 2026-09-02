package br.edu.ifba.security.feign;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignJwtInterceptor {

    private final HttpServletRequest request;

    public FeignJwtInterceptor(HttpServletRequest request) {
        this.request = request;
    }


    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {

            String authorization =
                    request.getHeader("Authorization");

            if (authorization != null) {
                template.header(
                    "Authorization",
                    authorization
                );
            }
        };
    }
}
