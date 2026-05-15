package com.navium.bff_sucursal.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AgendamientoDTO {
    private Long id;
    private Long idUsuario;
    private String correoUsuario;
    private String patenteCamion;
    private String rutChofer;
    private String tipoOperacion;
    private String idContenedor;
    private LocalDateTime horaInicio;
    private LocalDateTime bloqueFin;
    private String estadoAgendamiento;
    
}
