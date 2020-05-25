package org.devs.heythere_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private static final int MAX_AGES_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD","OPTIONS","GET","POST","PUT","DELETE","PATCH")
                .maxAge(MAX_AGES_SECS);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profile/**")
                .addResourceLocations("classpath:static/profile/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
    }
}
