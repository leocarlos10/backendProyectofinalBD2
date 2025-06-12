package com.leocarlos10.backendSG_medica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {
    private Integer id_comentario;
    private String descripcion;
    private String cedula_usuario;
    private Integer id_curso;
} 