package com.leocarlos10.backendSG_medica.dto.usuario;

import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.Models.Usuario;

import lombok.Data;

@Data
public class UsuarioDiagnosticoDTO {
    private Usuario usuario;
    private Diagnostico diagnostico;
}
