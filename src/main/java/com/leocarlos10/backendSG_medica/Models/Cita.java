package com.leocarlos10.backendSG_medica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private Integer id_cita;

    @NotNull(message = "La fecha y hora de la cita es requerida")
    private LocalDateTime fechaHora;

    @NotBlank(message = "El estado de la cita es requerido")
    private String estado;

    @NotBlank(message = "El motivo de consulta es requerido")
    private String motivoC;

    private String remitente;

    private LocalDate fechaU_Valoracion;

    @NotBlank(message = "La cédula del usuario es requerida")
    private String cedula_usuario;

    @NotBlank(message = "El tipo de cita es requerido")
    private String tipo_cita;

    @NotBlank(message = "El servicio es requerido")
    private String servicio;
}