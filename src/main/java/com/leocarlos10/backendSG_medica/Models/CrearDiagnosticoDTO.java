package com.leocarlos10.backendSG_medica.Models;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CrearDiagnosticoDTO {
    private String cedulaUsuario;
    private String tratamiento;
    private String observaciones;
    private String notaCorta;
    private String notaLarga;
    private LocalDate fecha;
}
