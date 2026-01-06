package com.leocarlos10.backendSG_medica.Models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @NotBlank(message = "La cédula es requerida")
    private String cedula;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotBlank(message = "El correo es requerido")
    @Email(message = "El correo debe ser válido")
    private String correo;

    @NotBlank(message = "El teléfono es requerido")
    private String telefono;

    @NotBlank(message = "La ciudad es requerida")
    private String ciudad;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String pass;

    private LocalDate fechaNacimiento;

    public Usuario(String cedula, String nombre, String apellido, String correo, String telefono, String ciudad,
            LocalDate fechaNacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
    }
}