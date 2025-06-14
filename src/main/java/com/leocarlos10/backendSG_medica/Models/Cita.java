package com.leocarlos10.backendSG_medica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private Integer id_cita;
    private LocalDateTime fechaHora;
    private String estado;
    private String motivoC;
    private String remitente;
    private LocalDate fechaU_Valoracion;
    private String cedula_usuario;
    private String tipo_cita;
    private String servicio;

    public Cita(LocalDateTime fechaHora, String estado, String motivoC, String remitente, LocalDate fechaU_Valoracion, String cedula_usuario, String tipo_cita) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.motivoC = motivoC;
        this.remitente = remitente;
        this.fechaU_Valoracion = fechaU_Valoracion;
        this.cedula_usuario = cedula_usuario;
        this.tipo_cita = tipo_cita;
    }
} 