package com.navium.bff_sucursal.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.navium.bff_sucursal.dto.AndenDTO;

@FeignClient(name = "ms-andenes", url = "${ANDENES_URL:http://localhost:8084/api/v0}")
public interface AndenClient {

    @GetMapping("/andenes")
    List<AndenDTO> obtenerTodos();

    @GetMapping("/andenes/{id}")
    AndenDTO obtenerPorId(@PathVariable("id") Long id);

    @GetMapping("/andenes/disponibles")
    List<AndenDTO> obtenerDisponibles();

    @GetMapping("/andenes/ocupados")
    List<AndenDTO> obtenerOcupados();

    @GetMapping("/andenes/zona/{zona}")
    List<AndenDTO> obtenerPorZona(@PathVariable("zona") String zona);

    @GetMapping("/andenes/codigo/{codigo}")
    AndenDTO obtenerPorCodigo(@PathVariable("codigo") String codigo);
}