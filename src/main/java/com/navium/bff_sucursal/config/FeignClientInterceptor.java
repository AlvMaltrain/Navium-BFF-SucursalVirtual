package com.navium.bff_sucursal.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            // Auth por cookie httpOnly: reenvía la cookie de sesión a los microservicios
            String cookie = attributes.getRequest().getHeader("Cookie");
            if (cookie != null) {
                template.header("Cookie", cookie);
            }

            // Compatibilidad: si llegara un header Authorization, también se reenvía
            String authHeader = attributes.getRequest().getHeader("Authorization");
            if (authHeader != null) {
                template.header("Authorization", authHeader);
            }
        }
    }
}