package com.navium.bff_sucursal.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * El BFF corre detrás del API Gateway, que ya maneja el CORS hacia el navegador.
 * La librería de seguridad valida el header Origin y rechaza los orígenes de S3
 * con "Invalid CORS request". Como el API Gateway no permite quitar el header
 * Origin (está restringido), lo eliminamos aquí, ANTES de que la cadena de
 * Spring Security lo evalúe. Sin Origin, Security no trata la petición como CORS
 * y la deja pasar (igual que una petición sin navegador).
 *
 * Se ejecuta con la máxima prioridad para correr antes del FilterChainProxy de Security.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StripOriginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest http = (HttpServletRequest) request;

        HttpServletRequest sinOrigin = new HttpServletRequestWrapper(http) {
            @Override
            public String getHeader(String name) {
                if ("Origin".equalsIgnoreCase(name)) {
                    return null;
                }
                return super.getHeader(name);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                if ("Origin".equalsIgnoreCase(name)) {
                    return Collections.emptyEnumeration();
                }
                return super.getHeaders(name);
            }
        };

        chain.doFilter(sinOrigin, response);
    }
}
