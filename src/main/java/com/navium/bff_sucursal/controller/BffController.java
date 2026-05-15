package com.navium.bff_sucursal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.navium.bff_sucursal.client.AgendamientoClient;
import com.navium.bff_sucursal.client.AndenClient;
import com.navium.bff_sucursal.client.ContenedorClient;
import com.navium.bff_sucursal.client.UsuarioClient;
import com.navium.bff_sucursal.dto.AgendamientoDTO;
import com.navium.bff_sucursal.dto.AgendamientoRequestDTO;
import com.navium.bff_sucursal.dto.AndenDTO;
import com.navium.bff_sucursal.dto.ConsultaCompletaDTO;
import com.navium.bff_sucursal.dto.ContenedorDTO;
import com.navium.bff_sucursal.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/bff")
@CrossOrigin(origins = "http:localhost:5173")
public class BffController {
    
    @Autowired
    private AgendamientoClient agendamientoClient;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private AndenClient andenClient;

    @Autowired
    private ContenedorClient contenedorClient;

    @GetMapping("/agendamientos")
    public List<AgendamientoDTO> listarTodos() {
        return agendamientoClient.obtenerTodos();
    }

    @GetMapping("/consulta-rapida/{patente}")
    public List<AgendamientoDTO> consultaRapida(@PathVariable String patente) {
        return agendamientoClient.obtenerPorPatente(patente);
    
    }    

    @GetMapping("/agendamiento/{id}")
    public AgendamientoDTO obtenerAgendamiento(@PathVariable Long id) {
        return agendamientoClient.obtenerPorId(id);
    }

    @GetMapping("/agendamiento/estado/{estado}")
    public List<AgendamientoDTO> listarPorEstado(@PathVariable String estado)  {
        return agendamientoClient.obtenerPorEstado(estado);
    }
    
   @PostMapping("/agendamiento")
    public ResponseEntity<AgendamientoDTO> crearAgendamiento(@RequestBody AgendamientoRequestDTO agendamiento) {
    AgendamientoDTO creado = agendamientoClient.crearAgendamiento(agendamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/agendamiento/rut/{rut}")
    public List<AgendamientoDTO> buscarPorRutChofer(@PathVariable String rut) {
        return agendamientoClient.obtenerPorRutChofer(rut);
    }

    @PutMapping("/agendamiento/{id}/cancelar")
    public AgendamientoDTO cancelarAgendamiento(@PathVariable Long id) {
        return agendamientoClient.cancelarAgendamiento(id);
    }

    //Ejemplo: http://localhost:8082/api/bff/agendamientos/fechas?inicio=2026-05-01T00:00:00&fin=2026-05-31T23:59:59
    @GetMapping("agendamientos/fechas")
    public List<AgendamientoDTO> listarPorFechas(
        @RequestParam String inicio,
        @RequestParam String fin
    ) {
        return agendamientoClient.obtenerPorRangoFechas(inicio, fin);
    }

    //Ejemplo: http://localhost:8082/api/bff/agendamiento/consulta?id=1
    @GetMapping("/agendamiento/consulta")
    public AgendamientoDTO consultarAgendamiento(
        @RequestParam(required = false) String patente,
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String momento
    ) {
        return agendamientoClient.consultarAgendamiento(patente, id, momento);
    }

    //UsuarioClient
   @GetMapping("/usuario/{id}")
    public UsuarioDTO obtenerUsuario(@PathVariable Long id) {
        return usuarioClient.obtenerPorId(id);
    }   

    @GetMapping("/andenes")
    public List<AndenDTO> listarAndenes() {
        return andenClient.obtenerTodos();
    }

    @GetMapping("/andenes/{id}")
    public AndenDTO obtenerAnden(@PathVariable Long id) {
        return andenClient.obtenerPorId(id);
    }

    @GetMapping("/andenes/disponibles")
    public List<AndenDTO> listarAndenesDisponibles() {
        return andenClient.obtenerDisponibles();
    }

    @GetMapping("/andenes/ocupados")
    public List<AndenDTO> listarAndenesOcupados() {
        return andenClient.obtenerOcupados();
    }

    @GetMapping("/andenes/zona/{zona}")
    public List<AndenDTO> listarAndenesPorZona(@PathVariable String zona) {
        return andenClient.obtenerPorZona(zona);
    }

    @GetMapping("/andenes/codigo/{codigo}")
    public AndenDTO obtenerAndenPorCodigo(@PathVariable String codigo) {
        return andenClient.obtenerPorCodigo(codigo);
    }

    @GetMapping("/contenedores")
    public List<ContenedorDTO> listarContenedores() {
        return contenedorClient.obtenerTodos();
    }

    @GetMapping("/contenedor/{id}")
    public ContenedorDTO obtenerContenedor(@PathVariable Long id) {
        return contenedorClient.obtenerPorId(id);
    }

    @GetMapping("/contenedores/patio")
    public List<ContenedorDTO> listarContenedoresEnPatio() {
        return contenedorClient.obtenerEnPatio();
    }

    @GetMapping("/consulta-completa/{patente}")
    public ConsultaCompletaDTO consultaCompleta(@PathVariable String patente) {
    List<AgendamientoDTO> agendamientos = agendamientoClient.obtenerPorPatente(patente);
    
    AgendamientoDTO agendamiento = agendamientos != null && !agendamientos.isEmpty() 
        ? agendamientos.get(0) 
        : null;

    ContenedorDTO contenedor = null;
    if (agendamiento != null && agendamiento.getIdContenedor() != null) {
        List<ContenedorDTO> todos = contenedorClient.obtenerTodos();
        contenedor = todos.stream()
            .filter(c -> c.getCodigoSigla().equals(agendamiento.getIdContenedor()))
            .findFirst()
            .orElse(null);
    }

    ConsultaCompletaDTO respuesta = new ConsultaCompletaDTO();
    respuesta.setAgendamiento(agendamiento);
    respuesta.setContenedor(contenedor);
    return respuesta;
}
}
