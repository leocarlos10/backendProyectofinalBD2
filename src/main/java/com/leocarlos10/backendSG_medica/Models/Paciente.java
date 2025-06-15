package com.leocarlos10.backendSG_medica.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    private Integer id_paciente;
    private String nombre;
    private String apellido;
    private String cedula;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDateTime ultima_cita;
    private String estado;

}
