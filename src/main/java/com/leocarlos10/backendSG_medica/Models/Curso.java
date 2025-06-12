package com.leocarlos10.backendSG_medica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    private Integer id_curso;
    private String titulo;
    private String descripcion;
    private LocalDate fecha_publicacion;
} 