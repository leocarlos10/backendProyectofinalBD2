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

    public Usuario(String cedula, String nombre, String apellido, String correo, String telefono, String ciudad, LocalDate fechaNacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
    }
} 