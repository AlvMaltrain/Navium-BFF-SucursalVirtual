package com.navium.bff_sucursal.dto;

public class ConsultaCompletaDTO {
    private AgendamientoDTO agendamiento;
    private ContenedorDTO contenedor;

    public AgendamientoDTO getAgendamiento() { return agendamiento; }
    public void setAgendamiento(AgendamientoDTO agendamiento) { this.agendamiento = agendamiento; }

    public ContenedorDTO getContenedor() { return contenedor; }
    public void setContenedor(ContenedorDTO contenedor) { this.contenedor = contenedor; }
}