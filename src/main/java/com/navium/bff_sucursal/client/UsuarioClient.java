package com.navium.bff_sucursal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.navium.bff_sucursal.dto.UsuarioDTO;

@FeignClient(name = "ms-usuarios", url = "${USUARIOS_URL:http://localhost:8085}")
public interface UsuarioClient {

    @GetMapping("/api/usuarios/{id}")
    UsuarioDTO obtenerPorId(@PathVariable("id") Long id);
}