package com.green.Shop.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final CheckAdminInterceptor adminInterceptor;
    private final MenuInterceptor menuInterceptor;
    private final CategoryInterceptor categoryInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //관리자 권한 체크를 하는 인터셉터의 실행 설정
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/**Fetch")
                .excludePathPatterns("/images/**", "/js/**", "/css/**");

        //메뉴 목록 조회 인터셉터의 실행 설정
        registry.addInterceptor(menuInterceptor)
                .addPathPatterns("/admin/**")
                //비동기 통신은 제외
                .excludePathPatterns("/**/**Fetch")
                .excludePathPatterns("/images/**", "/js/**", "/css/**");

        //카테고리 목록 조회 인터셉트의 실행 설정
        registry.addInterceptor(categoryInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/**/**Fetch")
                .excludePathPatterns("/images/**", "/js/**", "/css/**");
    }
}
