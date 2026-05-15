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
            String authHeader = attributes.getRequest().getHeader("Authorization");
            
            System.out.println("\n====== INTERCEPTOR BFF ACTIVADO ======");
            System.out.println("TOKEN RECIBIDO DESDE POSTMAN: " + authHeader);
            System.out.println("======================================\n");
            
            if (authHeader != null) {
                template.header("Authorization", authHeader);
            }
        }
    }
}