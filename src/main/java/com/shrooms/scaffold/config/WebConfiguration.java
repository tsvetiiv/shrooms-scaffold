package com.shrooms.scaffold.config;

import com.shrooms.scaffold.security.AdminInterceptor;
import com.shrooms.scaffold.security.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final AdminInterceptor adminInterceptor;

    public WebConfiguration(AuthenticationInterceptor authenticationInterceptor,
                            AdminInterceptor adminInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns(
                        "/orders/**",
                        "/users/profile/**",
                        "/scaffolds/purchase",
                        "/scaffolds/purchase/**",
                        "/scaffolds/rent",
                        "/scaffolds/rent/**",
                        "/custom-order/**",
                        "/admin/**"
                );

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");
    }
}