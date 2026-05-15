package com.navium.bff_sucursal.dto;

public class ContenedorDTO {
    private Long id;
    private String codigoSigla;
    private String tipoCarga;
    private String rutEmpresaTransporte;
    private String estadoBL;
    private String estadoTATC;
    private String estadoGeneral;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoSigla() { return codigoSigla; }
    public void setCodigoSigla(String codigoSigla) { this.codigoSigla = codigoSigla; }

    public String getTipoCarga() { return tipoCarga; }
    public void setTipoCarga(String tipoCarga) { this.tipoCarga = tipoCarga; }

    public String getRutEmpresaTransporte() { return rutEmpresaTransporte; }
    public void setRutEmpresaTransporte(String rut) { this.rutEmpresaTransporte = rut; }

    public String getEstadoBL() { return estadoBL; }
    public void setEstadoBL(String estadoBL) { this.estadoBL = estadoBL; }

    public String getEstadoTATC() { return estadoTATC; }
    public void setEstadoTATC(String estadoTATC) { this.estadoTATC = estadoTATC; }

    public String getEstadoGeneral() { return estadoGeneral; }
    public void setEstadoGeneral(String estadoGeneral) { this.estadoGeneral = estadoGeneral; }
}