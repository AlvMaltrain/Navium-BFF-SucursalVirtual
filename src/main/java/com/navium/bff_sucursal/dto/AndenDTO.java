package com.navium.bff_sucursal.dto;

public class AndenDTO {
    private Long id;
    private String zona;
    private int numero;
    private String codigo;
    private String tipo;
    private String estado;
    private String sector;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
}