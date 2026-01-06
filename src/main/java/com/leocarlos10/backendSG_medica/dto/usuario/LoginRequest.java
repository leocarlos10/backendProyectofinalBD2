package com.leocarlos10.backendSG_medica.dto.usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "La cédula no puede estar vacía")
    private String cedula;
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String pass;
}
