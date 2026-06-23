package com.navium.bff_sucursal.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.navium.bff_sucursal.dto.ContenedorDTO;

@FeignClient(name = "ms-contenedores", url = "${CONTENEDORES_URL:http://localhost:8086/api/contenedores}")
public interface ContenedorClient {

    @GetMapping
    List<ContenedorDTO> obtenerTodos();

    @GetMapping("/{id}")
    ContenedorDTO obtenerPorId(@PathVariable("id") Long id);

    @GetMapping("/patio")
    List<ContenedorDTO> obtenerEnPatio();
}