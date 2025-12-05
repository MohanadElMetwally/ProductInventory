package com.demoweb.ProductInventoryApp.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demoweb.ProductInventoryApp.security.CurrentUserArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CurrentUserArgumentResolver currentUserResolver;

    public WebConfig(CurrentUserArgumentResolver currentUserResolver) {
        this.currentUserResolver = currentUserResolver;
    }

    @Override
    public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserResolver);
    }
}