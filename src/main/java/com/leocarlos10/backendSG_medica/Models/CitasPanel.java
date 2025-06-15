package com.leocarlos10.backendSG_medica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitasPanel {
    
private Integer id_cita;
private String fechaHora;
private String estado;
private String motivoC;
private String remitente;
private String tipoCita;
private String nombreUsuario;
private String apellidoUsuario;
private String cedulaUsuario;
private String servicio;
    
}
