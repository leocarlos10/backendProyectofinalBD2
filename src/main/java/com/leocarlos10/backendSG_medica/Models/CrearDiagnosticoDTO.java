package com.leocarlos10.backendSG_medica.Models;

import lombok.Data;
import java.sql.Date;

@Data
public class CrearDiagnosticoDTO {
    private String cedulaUsuario;
    private String tratamiento;
    private String observaciones;
    private String notaCorta;
    private String notaLarga;
    private Date fecha;
}
