package com.leocarlos10.backendSG_medica.Models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String ciudad;
    private String pass;
    private LocalDate fechaNacimiento;
} 