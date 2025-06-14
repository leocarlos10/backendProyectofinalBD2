package com.leocarlos10.backendSG_medica.Models;

import lombok.Data;

@Data
public class UsuarioDiagnosticoDTO {
    private Usuario usuario;
    private Diagnostico diagnostico;
}
