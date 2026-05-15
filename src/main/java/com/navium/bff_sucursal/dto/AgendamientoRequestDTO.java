package com.navium.bff_sucursal.dto;

import lombok.Data;

@Data
public class AgendamientoRequestDTO {
    private Long idUsuario;
    private String correoUsuario;
    private String patenteCamion;
    private String rutChofer;
    private String horaInicio;
    private String tipoOperacion;
    private String idContenedor;
}