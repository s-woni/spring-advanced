package org.example.expert.config;

import lombok.RequiredArgsConstructor;
import org.example.expert.logging.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;

    // 모든 경로에 적용
    // 요구사항
    // 1. deleteComment()
    // 2. changeUserRole()
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(loggingInterceptor).addPathPatterns("/admin/users/{userId}", "/admin/comments/{commentId}");
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/admin/**");
    }

    // ArgumentResolver 등록
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthUserArgumentResolver());
    }
}
