package com.leocarlos10.backendSG_medica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pediatra {
    @NotBlank(message = "El usuario es requerido")
    private String usuario;

    @NotBlank(message = "La contraseña es requerida")
    private String pass;
}
