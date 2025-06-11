package com.leocarlos10.backendSG_medica.modelo;

import java.sql.Timestamp;
import java.util.Date;

public class CitaPacienteDTO {
    private Long idCita;
    private Timestamp fechahora;
    private String servicio;
    private String nombre;
    private String telefono;
    private Date fechaUltValoracion;
    private String motivoC;
    private String remitente;

    public CitaPacienteDTO() {
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public Timestamp getFechahora() {
        return fechahora;
    }

    public void setFechahora(Timestamp fechahora) {
        this.fechahora = fechahora;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaUltValoracion() {
        return fechaUltValoracion;
    }

    public void setFechaUltValoracion(Date fechaUltValoracion) {
        this.fechaUltValoracion = fechaUltValoracion;
    }

    public String getMotivoC() {
        return motivoC;
    }

    public void setMotivoC(String motivoC) {
        this.motivoC = motivoC;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }
}
