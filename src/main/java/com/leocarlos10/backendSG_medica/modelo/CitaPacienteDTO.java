package com.leocarlos10.backendSG_medica.modelo;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class CitaPacienteDTO {
    @Getter @Setter
    private Long idCita;
    @Getter @Setter
    private Timestamp fechahora;
    @Getter @Setter
    private String servicio;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String telefono;
    @Getter @Setter
    private Date fechaUltValoracion;
    @Getter @Setter
    private String motivoC;
    @Getter @Setter
    private String remitente;

    public CitaPacienteDTO() {
    }

    public CitaPacienteDTO(Long idCita, Timestamp fechahora, String servicio, String nombre, String telefono, Date fechaUltValoracion, String motivoC, String remitente) {
        this.idCita = idCita;
        this.fechahora = fechahora;
        this.servicio = servicio;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaUltValoracion = fechaUltValoracion;
        this.motivoC = motivoC;
        this.remitente = remitente;
    }
}
