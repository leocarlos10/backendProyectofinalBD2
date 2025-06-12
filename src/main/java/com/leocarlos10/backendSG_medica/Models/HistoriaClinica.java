package com.leocarlos10.backendSG_medica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaClinica {
    private Integer id_historia;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_creacion;
    private String cedula_usuario;
} 