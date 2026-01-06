package com.leocarlos10.backendSG_medica.dto.diagnostico;

import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CrearDiagnosticoDTO {
    @NotBlank(message = "La cédula del usuario es requerida")
    private String cedulaUsuario;

    @NotBlank(message = "El tratamiento es requerido")
    private String tratamiento;

    private String observaciones;

    @NotBlank(message = "La nota corta es requerida")
    private String notaCorta;

    private String notaLarga;

    @NotNull(message = "La fecha es requerida")
    private LocalDate fecha;
}
