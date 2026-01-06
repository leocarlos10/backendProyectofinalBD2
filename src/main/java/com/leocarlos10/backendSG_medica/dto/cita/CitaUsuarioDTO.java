package com.leocarlos10.backendSG_medica.dto.cita;

import com.leocarlos10.backendSG_medica.Models.Cita;
import com.leocarlos10.backendSG_medica.Models.Usuario;

import lombok.Data;

@Data
public class CitaUsuarioDTO {
    private Cita cita;
    private Usuario usuario;
}
