package com.navium.bff_sucursal.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.navium.bff_sucursal.dto.AgendamientoDTO;
import com.navium.bff_sucursal.dto.AgendamientoRequestDTO;

@FeignClient(name = "ms-agendamiento", url = "${AGENDAMIENTO_URL:http://localhost:8081/api/agendamientos}")
public interface AgendamientoClient {

    @GetMapping
    List<AgendamientoDTO> obtenerTodos();
    
    @GetMapping("/patente/{patente}")
    List<AgendamientoDTO> obtenerPorPatente(@PathVariable("patente") String patente);

    @GetMapping("{id}")
    AgendamientoDTO obtenerPorId(@PathVariable("id") Long id);

    @GetMapping("/estado/{estado}")
    List<AgendamientoDTO> obtenerPorEstado(@PathVariable("estado") String estado);

    @GetMapping("/rut/{rut}")
    List<AgendamientoDTO> obtenerPorRutChofer(@PathVariable("rut") String rut);

    @PostMapping
    AgendamientoDTO crearAgendamiento(@RequestBody AgendamientoRequestDTO agendamiento);

    @PutMapping("/{id}/cancelar")
    AgendamientoDTO cancelarAgendamiento(@PathVariable("id") Long id);

    @GetMapping("/fechas")
    List<AgendamientoDTO> obtenerPorRangoFechas(
        @RequestParam("inicio") String inicio,
        @RequestParam("fin") String fin
    );

    @GetMapping("/consulta")
    AgendamientoDTO consultarAgendamiento(
        @RequestParam(required = false) String patente,
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String momento
    );
}
