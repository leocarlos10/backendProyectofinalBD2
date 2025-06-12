package com.leocarlos10.backendSG_medica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {
    private Integer id_diagnostico;
    private String tratamiento;
    private String observaciones;
    private String nota_corta;
    private String nota_larga;
    private LocalDate fecha;
    private Integer id_historia;
} 