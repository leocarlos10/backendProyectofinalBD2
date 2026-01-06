package com.leocarlos10.backendSG_medica.dto.panel;

import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.Models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticoPanel {
    private Diagnostico diagnostico;
    private Usuario usuario;
    
}
